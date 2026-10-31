package co.com.botech.dto.preoperation;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Document breakdown")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class DocumentBreakdownDTO {
    @Schema(description = "Document")
    private String document;
    @Schema(description = "Document label")
    private String documentLabel;
    @Schema(description = "Valid count")
    private long vigente;
    @Schema(description = "Not valid count")
    private long noVigente;
    @Schema(description = "Total")
    private long total;
    @Schema(description = "Compliant rate")
    private double compliantRate;
    @Schema(description = "Non compliant rate")
    private double nonCompliantRate;
}
