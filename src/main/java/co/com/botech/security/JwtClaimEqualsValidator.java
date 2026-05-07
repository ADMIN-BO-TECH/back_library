package co.com.botech.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Objects;

public class JwtClaimEqualsValidator implements OAuth2TokenValidator<Jwt> {

    private final String claimName;
    private final String expectedValue;

    public JwtClaimEqualsValidator(String claimName, String expectedValue) {
        this.claimName = claimName;
        this.expectedValue = expectedValue;
    }

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        if (expectedValue == null || expectedValue.isBlank()) {
            return OAuth2TokenValidatorResult.success();
        }

        Object actual = token.getClaims().get(claimName);
        if (Objects.equals(expectedValue, actual)) {
            return OAuth2TokenValidatorResult.success();
        }

        OAuth2Error error = new OAuth2Error(
                "invalid_token",
                "Invalid JWT claim: expected '%s'='%s' but was '%s'".formatted(claimName, expectedValue, actual),
                null
        );
        return OAuth2TokenValidatorResult.failure(error);
    }
}
