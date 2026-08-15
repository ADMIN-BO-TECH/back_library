package co.com.botech.util.notification;

import co.com.botech.config.NotificationProperties;
import co.com.botech.dto.notification.FailedPushDelivery;
import co.com.botech.dto.notification.NotificationPayload;
import co.com.botech.dto.notification.NotificationSendResult;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MessagingErrorCode;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.SendResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class NotificationDispatcher {

    private final FirebaseMessaging firebaseMessaging;
    private final NotificationProperties props;

    public String sendToToken(String token, NotificationPayload payload) {
        if (token == null || token.isBlank()) {
            throw new NotificationSendException("token is required");
        }
        validatePayload(payload);
        try {
            Message message = buildMessageBuilder(payload).setToken(token).build();
            String messageId = firebaseMessaging.send(message);
            log.info("[FCM] Sent to token: {} -> {}", token, messageId);
            return messageId;
        } catch (FirebaseMessagingException e) {
            throw new NotificationSendException(
                    "Firebase error sending to token: " + token + " - " + e.getMessage(), e);
        }
    }

    public NotificationSendResult sendToTokens(List<String> tokens, NotificationPayload payload) {
        return sendToTokens(tokens, null, payload);
    }

    public NotificationSendResult sendToTokens(List<String> tokens,
                                               List<Long> userIds,
                                               NotificationPayload payload) {
        if (tokens == null || tokens.isEmpty()) {
            throw new NotificationSendException("tokens must not be empty");
        }
        if (userIds != null && userIds.size() != tokens.size()) {
            throw new NotificationSendException(
                    "userIds size must match tokens size (or be null)");
        }
        validatePayload(payload);

        List<String> sentUserTokens = new ArrayList<>();
        List<Long> sentUserIds = new ArrayList<>();
        List<FailedPushDelivery> failures = new ArrayList<>();

        int chunkSize = Math.min(props.getBulk().getChunkSize(), 500);
        for (int i = 0; i < tokens.size(); i += chunkSize) {
            int end = Math.min(i + chunkSize, tokens.size());
            List<String> tokenChunk = tokens.subList(i, end);
            List<Long> idChunk = userIds == null ? null : userIds.subList(i, end);

            sendChunk(tokenChunk, idChunk, payload, sentUserTokens, sentUserIds, failures);

            sleepQuietly(props.getBulk().getChunkDelayMs());
        }

        return NotificationSendResult.builder()
                .totalRequested(tokens.size())
                .sent(sentUserTokens.size())
                .failed(failures.size())
                .sentUserIds(sentUserIds)
                .failures(failures)
                .build();
    }

    private void sendChunk(List<String> tokenChunk,
                           List<Long> idChunk,
                           NotificationPayload payload,
                           List<String> sentUserTokens,
                           List<Long> sentUserIds,
                           List<FailedPushDelivery> failures) {
        MulticastMessage multicast = buildMulticastBuilder(payload)
                .addAllTokens(tokenChunk)
                .build();
        try {
            BatchResponse response = firebaseMessaging.sendEachForMulticast(multicast);
            List<SendResponse> responses = response.getResponses();
            for (int j = 0; j < responses.size(); j++) {
                SendResponse r = responses.get(j);
                String token = tokenChunk.get(j);
                Long userId = idChunk == null ? null : idChunk.get(j);
                if (r.isSuccessful()) {
                    sentUserTokens.add(token);
                    if (userId != null) sentUserIds.add(userId);
                } else {
                    failures.add(toFailure(userId, token, r.getException()));
                }
            }
        } catch (FirebaseMessagingException e) {
            log.error("[FCM] Batch send failed for chunk of {} tokens", tokenChunk.size(), e);
            for (int j = 0; j < tokenChunk.size(); j++) {
                Long userId = idChunk == null ? null : idChunk.get(j);
                failures.add(toFailure(userId, tokenChunk.get(j), e));
            }
        }
    }

    private FailedPushDelivery toFailure(Long userId, String token, FirebaseMessagingException e) {
        MessagingErrorCode code = e == null ? null : e.getMessagingErrorCode();
        boolean invalidated = code == MessagingErrorCode.UNREGISTERED
                || code == MessagingErrorCode.INVALID_ARGUMENT;
        return FailedPushDelivery.builder()
                .userId(userId)
                .token(token)
                .errorCode(code == null ? "UNKNOWN" : code.name())
                .message(e == null ? "unknown error" : e.getMessage())
                .tokenInvalidated(invalidated)
                .build();
    }

    private Message.Builder buildMessageBuilder(NotificationPayload payload) {
        Notification.Builder notification = Notification.builder()
                .setTitle(payload.getTitle())
                .setBody(payload.getBody());
        if (payload.getImageUrl() != null && !payload.getImageUrl().isBlank()) {
            notification.setImage(payload.getImageUrl());
        }
        Message.Builder builder = Message.builder()
                .setNotification(notification.build())
                .setAndroidConfig(defaultAndroidConfig())
                .setApnsConfig(defaultApnsConfig());
        Map<String, String> data = payload.getData();
        if (data != null && !data.isEmpty()) {
            builder.putAllData(data);
        }
        return builder;
    }

    private MulticastMessage.Builder buildMulticastBuilder(NotificationPayload payload) {
        Notification.Builder notification = Notification.builder()
                .setTitle(payload.getTitle())
                .setBody(payload.getBody());
        if (payload.getImageUrl() != null && !payload.getImageUrl().isBlank()) {
            notification.setImage(payload.getImageUrl());
        }
        MulticastMessage.Builder builder = MulticastMessage.builder()
                .setNotification(notification.build())
                .setAndroidConfig(defaultAndroidConfig())
                .setApnsConfig(defaultApnsConfig());
        Map<String, String> data = payload.getData();
        if (data != null && !data.isEmpty()) {
            builder.putAllData(data);
        }
        return builder;
    }

    private AndroidConfig defaultAndroidConfig() {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(props.getDefaultTtlMinutes()).toMillis())
                .setPriority(AndroidConfig.Priority.HIGH)
                .build();
    }

    private ApnsConfig defaultApnsConfig() {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setContentAvailable(true).build())
                .build();
    }

    private void validatePayload(NotificationPayload payload) {
        if (payload == null) {
            throw new NotificationSendException("payload is required");
        }
        if (payload.getTitle() == null || payload.getTitle().isBlank()) {
            throw new NotificationSendException("payload.title is required");
        }
        if (payload.getBody() == null || payload.getBody().isBlank()) {
            throw new NotificationSendException("payload.body is required");
        }
    }

    private static void sleepQuietly(long ms) {
        if (ms <= 0) return;
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
