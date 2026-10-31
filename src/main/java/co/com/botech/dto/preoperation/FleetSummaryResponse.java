package co.com.botech.dto.preoperation;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Fleet summary")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class FleetSummaryResponse {
    @Schema(description = "Period")
    private PreopPeriodDTO period;
    @Schema(description = "Total pre-operational checks")
    private long totalPreops;
    @Schema(description = "Pre-operational checks with issues")
    private long preopsWithIssues;
    @Schema(description = "Pre-operational checks with critical issues")
    private long preopsWithCritical;
    @Schema(description = "Pre-operational checks without issues")
    private long preopsClean;
    @Schema(description = "Total vehicles")
    private long totalVehicles;
    @Schema(description = "Vehicles with issues")
    private long vehiclesWithIssues;
    @Schema(description = "Vehicles with critical issues")
    private long vehiclesWithCritical;
    @Schema(description = "Issue rate")
    private double issueRate;
    @Schema(description = "Critical rate")
    private double criticalRate;
    @Schema(description = "Clean rate")
    private double cleanRate;
}
