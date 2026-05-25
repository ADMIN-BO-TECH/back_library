package co.com.botech.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@AutoConfiguration
@ConditionalOnClass({SecurityFilterChain.class, HttpSecurity.class})
public class SpringSecurityConfig {

    @Bean
    @ConditionalOnMissingBean(SecurityFilterChain.class)
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/schedule/**","/family/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}))
                .exceptionHandling(ex -> ex.authenticationEntryPoint((request, response, authException) -> {

                    Throwable root = rootCause(authException);

                    log.warn("401 Unauthorized {} {} | ex={} | root={}",
                            request.getMethod(),
                            request.getRequestURI(),
                            authException.getClass().getSimpleName(),
                            root == null ? "null" : (root.getClass().getSimpleName() + ": " + root.getMessage())
                    );

                    ErrorPayload payload = mapAuthError(authException, root);

                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter().write(payload.toJson());
                }));

        return http.build();
    }

    private ErrorPayload mapAuthError(Exception authException, Throwable root) {
        String msg = (root != null ? root.getMessage() : authException.getMessage());

        if (msg != null && msg.toLowerCase().contains("missing") && msg.toLowerCase().contains("bearer")) {
            return new ErrorPayload("TOKEN_MISSING", "Missing Bearer token", msg);
        }
        if (root instanceof BadJwtException) {
            String m = root.getMessage() == null ? "Invalid JWT" : root.getMessage();
            String low = m.toLowerCase();
            if (low.contains("expired")) return new ErrorPayload("TOKEN_EXPIRED", "Token expired", m);
            if (low.contains("issuer") || low.contains("iss"))
                return new ErrorPayload("ISSUER_INVALID", "Invalid token issuer", m);
            return new ErrorPayload("TOKEN_INVALID", "Invalid token", m);
        }
        if (root instanceof JwtValidationException jve) {
            String combined = String.join(" | ", jve.getErrors().stream()
                    .map(e -> e.getDescription() == null ? e.toString() : e.getDescription())
                    .toList());
            String low = combined.toLowerCase();
            if (low.contains("expired")) return new ErrorPayload("TOKEN_EXPIRED", "Token expired", combined);
            if (low.contains("issuer") || low.contains("iss"))
                return new ErrorPayload("ISSUER_INVALID", "Invalid token issuer", combined);
            if (low.contains("system")) return new ErrorPayload("SYSTEM_INVALID", "Invalid system claim", combined);
            return new ErrorPayload("TOKEN_INVALID", "Token validation failed", combined);
        }
        String rootText = root == null ? "" : String.valueOf(root.getMessage()).toLowerCase();
        if (rootText.contains("jwk") || rootText.contains("unable to retrieve") || rootText.contains("remote")) {
            return new ErrorPayload("JWKS_UNREACHABLE", "Cannot validate token (JWKS unreachable)", rootText);
        }
        if (authException instanceof OAuth2AuthenticationException oae) {
            String desc = oae.getError() != null ? oae.getError().getDescription() : null;
            return new ErrorPayload("OAUTH2_ERROR", "Authentication error", desc);
        }
        return new ErrorPayload("UNAUTHORIZED", "Invalid or missing token", msg);
    }

    private Throwable rootCause(Throwable t) {
        Throwable cur = t;
        while (cur != null && cur.getCause() != null && cur.getCause() != cur) cur = cur.getCause();
        return cur;
    }

    private record ErrorPayload(String code, String message, String detail) {
        String toJson() {
            return "{\"code\":\"" + safe(code) + "\","
                    + "\"message\":\"" + safe(message) + "\","
                    + "\"detail\":\"" + safe(detail) + "\"}";
        }
        private static String safe(String s) {
            if (s == null) return "";
            return s.replace("\"", "\\\"");
        }
    }
}
