package co.com.botech.dto.email.otp;

import jakarta.validation.constraints.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OTP validation")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class OtpValidateRequest {
    @Schema(description = "6-digit OTP from email", example = "482913")
    @NotBlank private String otpCode;
    @Schema(description = "applicationId (UUID) returned when generating the OTP", example = "3f6c1b2e-8a4d-4e2b-9c1a-7d5e2f4b6a10")
    @NotBlank private String applicationId;
    @Schema(description = "Email", example = "maria.perez@correo.com")
    @NotBlank @Email private String email;
    @Schema(description = "Product used when generating the OTP", example = "TRACKING")
    @NotBlank private String productKey;
}
