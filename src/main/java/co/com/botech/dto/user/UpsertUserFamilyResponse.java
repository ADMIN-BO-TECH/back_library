package co.com.botech.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertUserFamilyResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private String fcmToken;
    private String firebaseUid;
    private String familyCode;
    private String userType;
    private Boolean createdNewFamilyLink;
    private Boolean updatedUser;
}