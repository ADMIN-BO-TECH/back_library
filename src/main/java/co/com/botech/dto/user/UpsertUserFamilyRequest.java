package co.com.botech.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Create or update app user and family link")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpsertUserFamilyRequest {

    @Schema(description = "User ID", example = "501")
    @NotNull
    private Long userId;

    @Schema(description = "User's full name", example = "María Pérez")
    @NotBlank
    private String fullName;

    @Schema(description = "Device Firebase Cloud Messaging token", example = "fcm-token-de-ejemplo")
    private String fcmToken;

    @Schema(description = "User's Firebase UID", example = "Xy12AbCdEf34GhIj56KlMn")
    private String firebaseUid;

    @Schema(description = "Family code", example = "12345678")
    @NotBlank
    private String familyCode;

    @Schema(description = "Allowed: PARENT, SCHOOL_EMPLOYEE, ADMIN, STUDENT, COORDINATOR, CLIENT_EMPLOYEE, BOSS, RRHH", example = "PARENT")
    @NotBlank
    private String userType;
}
