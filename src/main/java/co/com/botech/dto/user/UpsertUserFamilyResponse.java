package co.com.botech.dto.user;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "App user create or update result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertUserFamilyResponse {

    @Schema(description = "User ID", example = "501")
    private Long userId;
    @Schema(description = "User's full name", example = "María Pérez")
    private String fullName;
    @Schema(description = "Device Firebase Cloud Messaging token", example = "fcm-token-de-ejemplo")
    private String fcmToken;
    @Schema(description = "User's Firebase UID", example = "Xy12AbCdEf34GhIj56KlMn")
    private String firebaseUid;
    @Schema(description = "Family code", example = "12345678")
    private String familyCode;
    @Schema(description = "User type (PARENT, SCHOOL_EMPLOYEE, STUDENT, ...)", example = "PARENT")
    private String userType;
    @Schema(description = "Whether a new family link was created", example = "true")
    private Boolean createdNewFamilyLink;
    @Schema(description = "Whether an existing user was updated", example = "false")
    private Boolean updatedUser;
}