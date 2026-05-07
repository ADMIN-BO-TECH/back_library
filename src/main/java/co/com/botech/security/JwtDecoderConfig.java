package co.com.botech.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnClass(NimbusJwtDecoder.class)
@EnableConfigurationProperties(JwtValidationProperties.class)
public class JwtDecoderConfig {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final TypeReference<Map<String, Object>> MAP_TYPE = new TypeReference<>() {};

    @Bean
    @ConditionalOnMissingBean
    public JwtDecoder jwtDecoder(JwtValidationProperties props) {

        if (isBlank(props.getJwkSetUri())) {
            throw new IllegalStateException("app.security.jwt.jwk-set-uri es requerido (RS256 via JWKS).");
        }
        if (isBlank(props.getIssuer())) {
            throw new IllegalStateException("app.security.jwt.issuer es requerido.");
        }

        NimbusJwtDecoder delegate = NimbusJwtDecoder
                .withJwkSetUri(props.getJwkSetUri())
                .jwsAlgorithm(SignatureAlgorithm.RS256)
                .build();

        OAuth2TokenValidator<Jwt> issuerValidator =
                JwtValidators.createDefaultWithIssuer(props.getIssuer());

        OAuth2TokenValidator<Jwt> systemValidator =
                new JwtClaimEqualsValidator("system", props.getExpectedSystem());

        delegate.setJwtValidator(new DelegatingOAuth2TokenValidator<>(issuerValidator, systemValidator));

        return token -> {
            try {
                return delegate.decode(token);
            } catch (JwtException ex) {
                JwtHeaderSafe header = parseHeaderSafely(token);
                log.warn(
                        "JWT decode/validation failed | alg={} kid={} | issExpected={} | jwks={} | msg={}",
                        header.alg(), header.kid(), props.getIssuer(), props.getJwkSetUri(), ex.getMessage()
                );
                throw ex;
            }
        };
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private record JwtHeaderSafe(String alg, String kid) {}

    private static JwtHeaderSafe parseHeaderSafely(String token) {
        try {
            if (token == null) return new JwtHeaderSafe(null, null);
            String[] parts = token.split("\\.");
            if (parts.length < 2) return new JwtHeaderSafe(null, null);
            byte[] jsonBytes = Base64.getUrlDecoder().decode(parts[0]);
            String json = new String(jsonBytes, StandardCharsets.UTF_8);
            Map<String, Object> header = MAPPER.readValue(json, MAP_TYPE);
            String alg = header.get("alg") == null ? null : String.valueOf(header.get("alg"));
            String kid = header.get("kid") == null ? null : String.valueOf(header.get("kid"));
            return new JwtHeaderSafe(alg, kid);
        } catch (Exception ignored) {
            return new JwtHeaderSafe(null, null);
        }
    }
}
