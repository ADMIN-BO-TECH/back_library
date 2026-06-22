package co.com.botech.util.email;

import co.com.botech.config.EmailProperties;
import co.com.botech.constants.EmailTemplateAssignation;
import co.com.botech.dto.email.EmailVariables;
import co.com.botech.dto.email.bulk.BulkEmailRequest;
import co.com.botech.dto.email.bulk.BulkEmailResult;
import co.com.botech.dto.email.bulk.FailedDelivery;
import co.com.botech.dto.email.bulk.PersonalizedBulkEmailRequest;
import co.com.botech.dto.email.bulk.PersonalizedRecipient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Slf4j
@Service
public class BulkEmailService {

    private final EmailDispatcher dispatcher;
    private final EmailProperties props;
    private final Executor executor;

    public BulkEmailService(EmailDispatcher dispatcher,
                            EmailProperties props,
                            @Qualifier("notificationExecutor") Executor executor) {
        this.dispatcher = dispatcher;
        this.props = props;
        this.executor = executor;
    }

    public <T extends EmailVariables> BulkEmailResult sendBulk(BulkEmailRequest<T> request) {
        validate(request.getRecipients());
        int chunkSize = resolveChunkSize(request.getChunkSize());
        List<List<String>> chunks = partition(request.getRecipients(), chunkSize);

        List<String> sent = Collections.synchronizedList(new ArrayList<>());
        List<FailedDelivery> failed = Collections.synchronizedList(new ArrayList<>());

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < chunks.size(); i++) {
            List<String> chunk = chunks.get(i);
            long delay = props.getBulk().getChunkDelayMs() * i;
            futures.add(CompletableFuture.runAsync(() -> {
                sleepQuietly(delay);
                for (String email : chunk) {
                    try {
                        dispatcher.sendWithTemplate(email, request.getSubject(),
                                request.getTemplate(), request.getVariables());
                        sent.add(email);
                    } catch (Exception ex) {
                        log.warn("Bulk send failed for {}: {}", email, ex.getMessage());
                        failed.add(new FailedDelivery(email, ex.getMessage()));
                    }
                }
            }, executor));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        return BulkEmailResult.builder()
                .totalRequested(request.getRecipients().size())
                .sent(sent.size())
                .failed(failed.size())
                .sentTo(sent)
                .failures(failed)
                .build();
    }

    public <T extends EmailVariables> BulkEmailResult sendPersonalized(PersonalizedBulkEmailRequest<T> request) {
        List<PersonalizedRecipient<T>> recipients = request.getRecipients();
        validate(recipients.stream().map(PersonalizedRecipient::getEmail).toList());
        int chunkSize = resolveChunkSize(request.getChunkSize());
        List<List<PersonalizedRecipient<T>>> chunks = partition(recipients, chunkSize);

        List<String> sent = Collections.synchronizedList(new ArrayList<>());
        List<FailedDelivery> failed = Collections.synchronizedList(new ArrayList<>());

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < chunks.size(); i++) {
            List<PersonalizedRecipient<T>> chunk = chunks.get(i);
            long delay = props.getBulk().getChunkDelayMs() * i;
            EmailTemplateAssignation template = request.getTemplate();
            String subject = request.getSubject();
            futures.add(CompletableFuture.runAsync(() -> {
                sleepQuietly(delay);
                for (PersonalizedRecipient<T> r : chunk) {
                    try {
                        dispatcher.sendWithTemplate(r.getEmail(), subject, template, r.getVariables());
                        sent.add(r.getEmail());
                    } catch (Exception ex) {
                        log.warn("Personalized bulk send failed for {}: {}", r.getEmail(), ex.getMessage());
                        failed.add(new FailedDelivery(r.getEmail(), ex.getMessage()));
                    }
                }
            }, executor));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        return BulkEmailResult.builder()
                .totalRequested(recipients.size())
                .sent(sent.size())
                .failed(failed.size())
                .sentTo(sent)
                .failures(failed)
                .build();
    }

    private void validate(List<String> recipients) {
        if (recipients == null || recipients.isEmpty()) {
            throw new IllegalArgumentException("recipients must not be empty");
        }
        int max = props.getBulk().getMaxRecipientsPerRequest();
        if (recipients.size() > max) {
            throw new IllegalArgumentException("recipients size " + recipients.size() + " exceeds max " + max);
        }
    }

    private int resolveChunkSize(Integer requested) {
        if (requested != null && requested > 0) return requested;
        return props.getBulk().getChunkSize();
    }

    private static <E> List<List<E>> partition(List<E> source, int size) {
        List<List<E>> out = new ArrayList<>();
        for (int i = 0; i < source.size(); i += size) {
            out.add(source.subList(i, Math.min(i + size, source.size())));
        }
        return out;
    }

    private static void sleepQuietly(long ms) {
        if (ms <= 0) return;
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}