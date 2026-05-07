package co.com.botech.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.security.jwt")
public class JwtValidationProperties {

    private String jwkSetUri;
    private String issuer;
    private String expectedSystem;
}
