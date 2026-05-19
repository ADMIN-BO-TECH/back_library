package co.com.botech.security;

import java.util.List;

public record SsoTokenClaims(
        Long userId,
        String clientCode,
        String system,
        Long activeProfileId,
        List<SsoProfileClaim> profiles,
        String firebaseUid
) {
}
