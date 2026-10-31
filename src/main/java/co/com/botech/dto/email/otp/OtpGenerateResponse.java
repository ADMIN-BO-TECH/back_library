package co.com.botech.dto.email.otp;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Otp generate")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class OtpGenerateResponse {
    @Schema(description = "Application ID")
    private String applicationId;
}