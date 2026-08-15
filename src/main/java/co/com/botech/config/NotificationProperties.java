package co.com.botech.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.notification")
public class NotificationProperties {

    private boolean persistHistory = true;

    private boolean cleanupInvalidTokens = true;

    private long defaultTtlMinutes = 2;

    private Bulk bulk = new Bulk();

    @Getter
    @Setter
    public static class Bulk {
        private int chunkSize = 500;
        private long chunkDelayMs = 0;
        private int maxRecipientsPerRequest = 5000;
    }
}
