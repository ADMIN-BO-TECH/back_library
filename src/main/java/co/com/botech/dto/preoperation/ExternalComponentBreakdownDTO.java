package co.com.botech.dto.preoperation;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "External component breakdown")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExternalComponentBreakdownDTO {
    @Schema(description = "Component")
    private String component;
    @Schema(description = "Component label")
    private String componentLabel;
    @Schema(description = "Total")
    private long total;
    @Schema(description = "Good count")
    private long bueno;
    @Schema(description = "Bad count")
    private long malo;
    @Schema(description = "Damaged count")
    private long danado;
    @Schema(description = "Bueno rate")
    private double buenoRate;
    @Schema(description = "Attention rate")
    private double attentionRate;
    @Schema(description = "Critical rate")
    private double criticalRate;
    @Schema(description = "Problem rate")
    private double problemRate;
}
