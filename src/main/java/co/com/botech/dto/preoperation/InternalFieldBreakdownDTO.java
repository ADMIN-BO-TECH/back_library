package co.com.botech.dto.preoperation;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Internal field breakdown")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class InternalFieldBreakdownDTO {
    @Schema(description = "Field")
    private String field;
    @Schema(description = "Field label")
    private String fieldLabel;
    @Schema(description = "Problematic")
    private long problematic;
    @Schema(description = "Ok")
    private long ok;
    @Schema(description = "Total")
    private long total;
    @Schema(description = "Problem rate")
    private double problemRate;
    @Schema(description = "Normal level count")
    private long levelNormal;
    @Schema(description = "Low level count")
    private long levelBajo;
}
