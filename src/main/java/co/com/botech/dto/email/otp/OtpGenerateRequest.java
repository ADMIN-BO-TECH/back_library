package co.com.botech.dto.email.otp;

import jakarta.validation.constraints.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OTP send request")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class OtpGenerateRequest {
    @Schema(description = "Recipient name", example = "María Pérez")
    @NotBlank private String name;
    @Schema(description = "Email", example = "maria.perez@correo.com")
    @NotBlank @Email private String email;
    @Schema(description = "Mobile number", example = "3001234567")
    private String phone;
    @Schema(description = "Requesting product; send the same value to validate", example = "TRACKING")
    @NotBlank private String productKey;
}