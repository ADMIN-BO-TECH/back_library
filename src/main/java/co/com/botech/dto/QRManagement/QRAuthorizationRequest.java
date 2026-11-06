package co.com.botech.dto.QRManagement;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Access check from a QR document")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QRAuthorizationRequest {
    @Schema(description = "Document number from the QR code", example = "1020304050")
    private String document;
    @Schema(description = "RFID reader ID recording the access", example = "3")
    private Long rfidRegisterId;
}
