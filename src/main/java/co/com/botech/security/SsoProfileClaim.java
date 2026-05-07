package co.com.botech.security;

public record SsoProfileClaim(
        Long profileId,
        String kindSlug,
        Long schoolId,
        String schoolName,
        Long systemId,
        String systemName
) {
}
