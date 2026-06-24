package co.com.botech.dto.email.otp;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class OtpValidateRequest {
    @NotBlank private String otpCode;
    @NotBlank private String iv;
    @NotBlank private String applicationId;
    @NotBlank @Email private String email;
    @NotBlank private String familyCode;
}
