package co.com.botech.dto.email.otp;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class OtpGenerateRequest {
    @NotBlank private String name;
    @NotBlank @Email private String email;
    private String phone;
    @NotBlank private String productKey;
}