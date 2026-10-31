package co.com.botech.dto.QRManagement;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "QR authorization")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QRAuthorizationResponse {
    @Schema(description = "Result message")
    private String message;
    @Schema(description = "Access is allowed")
    private boolean allowed;
    @Schema(description = "Name")
    private String name;
    @Schema(description = "Additional information")
    private String info;
    @Schema(description = "Access type")
    private String accessType;
}
