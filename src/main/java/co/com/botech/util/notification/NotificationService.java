package co.com.botech.util.notification;

import co.com.botech.config.NotificationProperties;
import co.com.botech.dto.notification.FailedPushDelivery;
import co.com.botech.dto.notification.NotificationPayload;
import co.com.botech.dto.notification.NotificationSendResult;
import co.com.botech.entity.NotificationCategory;
import co.com.botech.entity.NotificationHistory;
import co.com.botech.entity.User;
import co.com.botech.repository.NotificationCategoryRepository;
import co.com.botech.repository.NotificationHistoryRepository;
import co.com.botech.repository.UserDisabledNotificationRepository;
import co.com.botech.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Slf4j
public class NotificationService {

    private final NotificationDispatcher dispatcher;
    private final NotificationProperties props;
    private final UserRepository userRepository;
    private final NotificationCategoryRepository categoryRepository;
    private final NotificationHistoryRepository historyRepository;
    private final UserDisabledNotificationRepository disabledRepository;
    private final Executor executor;

    public NotificationService(NotificationDispatcher dispatcher,
                               NotificationProperties props,
                               UserRepository userRepository,
                               NotificationCategoryRepository categoryRepository,
                               NotificationHistoryRepository historyRepository,
                               UserDisabledNotificationRepository disabledRepository,
                               @Qualifier("notificationExecutor") Executor executor) {
        this.dispatcher = dispatcher;
        this.props = props;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.historyRepository = historyRepository;
        this.disabledRepository = disabledRepository;
        this.executor = executor;
    }

    @Transactional
    public NotificationSendResult sendToUserIds(Collection<Long> userIds, NotificationPayload payload) {
        if (userIds == null || userIds.isEmpty()) {
            return emptyResult();
        }
        if (payload == null) {
            throw new NotificationSendException("payload is required");
        }

        NotificationCategory category = resolveCategoryOrNull(payload.getCategoryName());
        if (category != null && Boolean.FALSE.equals(category.getGlobalEnabled())) {
            log.info("[Notification] Category '{}' globally disabled — skipping send",
                    payload.getCategoryName());
            NotificationSendResult skipped = emptyResult();
            skipped.setTotalRequested(userIds.size());
            skipped.setSkippedByPreference(userIds.size());
            return skipped;
        }

        List<User> users = userRepository.findAllById(userIds);
        if (users.isEmpty()) {
            log.warn("[Notification] No users resolved for ids: {}", userIds);
            return emptyResult();
        }

        Set<Long> disabledIds = category == null
                ? Collections.emptySet()
                : disabledRepository.findDisabledUserIdsByCategoryAndUserIds(
                        category.getId(),
                        users.stream().map(User::getId).toList());

        int totalRequested = users.size();
        int skippedByPref = 0;

        List<String> tokens = new ArrayList<>();
        List<Long> tokenUserIds = new ArrayList<>();
        for (User u : users) {
            if (disabledIds.contains(u.getId())) {
                skippedByPref++;
                continue;
            }
            String tok = u.getFcmToken();
            if (tok == null || tok.isBlank()) {
                skippedByPref++;
                continue;
            }
            tokens.add(tok);
            tokenUserIds.add(u.getId());
        }

        if (tokens.isEmpty()) {
            NotificationSendResult empty = emptyResult();
            empty.setTotalRequested(totalRequested);
            empty.setSkippedByPreference(skippedByPref);
            return empty;
        }

        NotificationSendResult sendResult = dispatcher.sendToTokens(tokens, tokenUserIds, payload);
        sendResult.setTotalRequested(totalRequested);
        sendResult.setSkippedByPreference(skippedByPref);

        if (props.isPersistHistory()) {
            persistHistory(sendResult.getSentUserIds(), users, payload, category);
        }

        if (props.isCleanupInvalidTokens()) {
            List<Long> invalidUserIds = sendResult.getFailures().stream()
                    .filter(FailedPushDelivery::isTokenInvalidated)
                    .map(FailedPushDelivery::getUserId)
                    .filter(id -> id != null)
                    .toList();
            if (!invalidUserIds.isEmpty()) {
                int cleared = userRepository.clearFcmTokens(invalidUserIds);
                sendResult.setInvalidatedTokens(cleared);
                log.info("[Notification] Cleared {} invalid FCM tokens", cleared);
            }
        }

        return sendResult;
    }

    public NotificationSendResult sendToUserId(Long userId, NotificationPayload payload) {
        return sendToUserIds(Collections.singletonList(userId), payload);
    }

    @Async("notificationExecutor")
    public CompletableFuture<NotificationSendResult> sendToUserIdsAsync(Collection<Long> userIds,
                                                                        NotificationPayload payload) {
        try {
            return CompletableFuture.completedFuture(sendToUserIds(userIds, payload));
        } catch (Exception e) {
            log.error("[Notification] Async send failed", e);
            CompletableFuture<NotificationSendResult> failed = new CompletableFuture<>();
            failed.completeExceptionally(e);
            return failed;
        }
    }

    private NotificationCategory resolveCategoryOrNull(String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            return null;
        }
        return categoryRepository.findByCategoryName(categoryName).orElse(null);
    }

    private void persistHistory(List<Long> sentUserIds,
                                List<User> users,
                                NotificationPayload payload,
                                NotificationCategory category) {
        if (sentUserIds == null || sentUserIds.isEmpty()) return;

        Set<Long> sentIdSet = new HashSet<>(sentUserIds);
        Map<Long, User> userById = users.stream()
                .collect(Collectors.toMap(User::getId, u -> u));

        LocalDateTime now = LocalDateTime.now();
        List<NotificationHistory> history = new ArrayList<>(sentIdSet.size());
        for (Long id : sentIdSet) {
            User u = userById.get(id);
            if (u == null) continue;
            history.add(NotificationHistory.builder()
                    .user(u)
                    .category(category)
                    .title(payload.getTitle())
                    .body(payload.getBody())
                    .sentAt(now)
                    .build());
        }
        historyRepository.saveAll(history);
    }

    private static NotificationSendResult emptyResult() {
        return NotificationSendResult.builder()
                .totalRequested(0)
                .sent(0)
                .failed(0)
                .skippedByPreference(0)
                .invalidatedTokens(0)
                .sentUserIds(new ArrayList<>())
                .failures(new ArrayList<>())
                .build();
    }
}
