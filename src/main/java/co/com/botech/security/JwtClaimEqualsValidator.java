package co.com.botech.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;

public class JwtClaimEqualsValidator implements OAuth2TokenValidator<Jwt> {

    private final String claimName;
    private final Collection<String> allowedValues;

    public JwtClaimEqualsValidator(String claimName, Collection<String> allowedValues) {
        this.claimName = claimName;
        this.allowedValues = allowedValues;
    }

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        if (allowedValues == null || allowedValues.isEmpty()) {
            return OAuth2TokenValidatorResult.success();
        }

        Object actual = token.getClaims().get(claimName);
        if (actual != null && allowedValues.contains(actual.toString())) {
            return OAuth2TokenValidatorResult.success();
        }

        OAuth2Error error = new OAuth2Error(
                "invalid_token",
                "Invalid JWT claim: '%s'='%s' is not in allowed values %s".formatted(claimName, actual, allowedValues),
                null
        );
        return OAuth2TokenValidatorResult.failure(error);
    }
}
