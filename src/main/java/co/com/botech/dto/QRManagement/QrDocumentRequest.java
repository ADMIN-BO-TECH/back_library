package co.com.botech.dto.QRManagement;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Person search from a QR document")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QrDocumentRequest {
    @Schema(description = "Document number from the QR code", example = "1020304050")
    private String document;
}