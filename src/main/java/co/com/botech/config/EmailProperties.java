package co.com.botech.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.email")
public class EmailProperties {

    private String host = "smtp.gmail.com";
    private int port = 587;
    private String username;
    private String password;
    private String from;
    private boolean auth = true;
    private boolean starttls = true;
    private String trust = "smtp.gmail.com";

    private Bulk bulk = new Bulk();

    @Getter
    @Setter
    public static class Bulk {
        private int chunkSize = 50;
        private long chunkDelayMs = 0;
        private int maxRecipientsPerRequest = 1000;
    }
}