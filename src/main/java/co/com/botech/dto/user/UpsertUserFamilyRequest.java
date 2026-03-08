package co.com.botech.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpsertUserFamilyRequest {

    @NotNull
    private Long userId;

    @NotBlank
    private String fullName;

    private String fcmToken;

    private String firebaseUid;

    @NotBlank
    private String familyCode;

    @NotBlank
    private String userType;
}
