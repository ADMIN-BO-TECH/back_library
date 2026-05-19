package co.com.botech.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@ConditionalOnClass(JwtAuthenticationToken.class)
public class SsoAuthContext {

    public static final String CLAIM_CLIENT_CODE = "client_code";
    public static final String CLAIM_SYSTEM = "system";
    public static final String CLAIM_ACTIVE_PROFILE_ID = "activeProfileId";
    public static final String CLAIM_PROFILES = "profiles";
    public static final String CLAIM_SCHOOL_ID = "school_id";
    public static final String CLAIM_SCHOOL_NAME = "school_name";
    public static final String CLAIM_FIREBASE_UID = "firebase_uid";

    public Jwt currentJwt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            return jwtAuth.getToken();
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No JWT authentication found");
    }

    public Long getUserId() {
        String sub = currentJwt().getSubject();
        if (sub == null || sub.isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT subject (sub) is missing");
        }
        try {
            return Long.valueOf(sub);
        } catch (NumberFormatException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT subject (sub) is not a numeric userId");
        }
    }

    public String getClientCode() {
        return currentJwt().getClaimAsString(CLAIM_CLIENT_CODE);
    }

    public String getClientCodeOrThrow() {
        String client = getClientCode();
        if (client == null || client.isBlank()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Missing claim 'client_code' in JWT");
        }
        return client;
    }

    public String getSystem() {
        return currentJwt().getClaimAsString(CLAIM_SYSTEM);
    }

    public String getSystemOrThrow() {
        String system = getSystem();
        if (system == null || system.isBlank()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Missing claim 'system' in JWT");
        }
        return system;
    }

    public Long getSchoolId() {
        Object raw = currentJwt().getClaims().get(CLAIM_SCHOOL_ID);
        return toLong(raw);
    }

    public Long getSchoolIdOrThrow() {
        Long id = getSchoolId();
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Missing claim 'school_id' in JWT");
        }
        return id;
    }

    public String getSchoolName() {
        return currentJwt().getClaimAsString(CLAIM_SCHOOL_NAME);
    }

    public String getSchoolNameOrThrow() {
        String name = getSchoolName();
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Missing claim 'school_name' in JWT");
        }
        return name;
    }

    public String getFirebaseUid() {
        return currentJwt().getClaimAsString(CLAIM_FIREBASE_UID);
    }

    public String getFirebaseUidOrThrow() {
        String uid = getFirebaseUid();
        if (uid == null || uid.isBlank()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Missing claim 'firebase_uid' in JWT");
        }
        return uid;
    }

    public Long getActiveProfileId() {
        Object raw = currentJwt().getClaims().get(CLAIM_ACTIVE_PROFILE_ID);
        return toLong(raw);
    }

    @SuppressWarnings("unchecked")
    public List<SsoProfileClaim> getProfiles() {
        Object raw = currentJwt().getClaims().get(CLAIM_PROFILES);
        if (!(raw instanceof List<?> list)) return List.of();

        return list.stream()
                .filter(it -> it instanceof Map<?, ?>)
                .map(it -> (Map<String, Object>) it)
                .map(this::mapProfile)
                .toList();
    }

    public SsoProfileClaim getActiveProfileOrNull() {
        Long activeId = getActiveProfileId();
        if (activeId == null) return null;

        return getProfiles().stream()
                .filter(p -> Objects.equals(p.profileId(), activeId))
                .findFirst()
                .orElse(null);
    }

    public SsoProfileClaim getActiveProfileOrThrow() {
        SsoProfileClaim active = getActiveProfileOrNull();
        if (active == null) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Active profile not found. Missing/invalid activeProfileId or profiles claim."
            );
        }
        return active;
    }

    public SsoTokenClaims getClaims() {
        return new SsoTokenClaims(
                getUserId(),
                getClientCode(),
                getSystem(),
                getActiveProfileId(),
                getProfiles(),
                getFirebaseUid()
        );
    }

    private SsoProfileClaim mapProfile(Map<String, Object> m) {
        return new SsoProfileClaim(
                toLong(m.get("profileId")),
                toStr(m.get("kindSlug")),
                toLong(m.get("schoolId")),
                toStr(m.get("schoolName")),
                toLong(m.get("systemId")),
                toStr(m.get("systemName"))
        );
    }

    private Long toLong(Object v) {
        if (v == null) return null;
        if (v instanceof Number n) return n.longValue();
        try { return Long.valueOf(String.valueOf(v)); } catch (Exception e) { return null; }
    }

    private String toStr(Object v) {
        return v == null ? null : String.valueOf(v);
    }
}
