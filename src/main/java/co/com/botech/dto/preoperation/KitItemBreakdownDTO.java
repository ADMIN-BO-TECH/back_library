package co.com.botech.dto.preoperation;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Kit item breakdown")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KitItemBreakdownDTO {
    @Schema(description = "Item")
    private String item;
    @Schema(description = "Item label")
    private String itemLabel;
    @Schema(description = "Yes count")
    private long si;
    @Schema(description = "No count")
    private long no;
    @Schema(description = "Issue count")
    private long novedad;
    @Schema(description = "Total")
    private long total;
    @Schema(description = "Compliant rate")
    private double compliantRate;
    @Schema(description = "No rate")
    private double noRate;
    @Schema(description = "Issue rate")
    private double novedadRate;
}
