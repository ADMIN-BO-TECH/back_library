package co.com.botech.dto.preoperation;

import lombok.*;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicle ranking item")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class VehicleRankingItemDTO {
    @Schema(description = "Vehicle ID")
    private Long vehicleId;
    @Schema(description = "Plate number")
    private String plateNumber;
    @Schema(description = "Fleet number")
    private String fleetNumber;
    @Schema(description = "School name")
    private String colegioName;

    @Schema(description = "Total pre-operational checks")
    private long totalPreops;
    @Schema(description = "Pre-operational checks with issues")
    private long preopsWithIssues;
    @Schema(description = "Issue rate")
    private double issueRate;
    @Schema(description = "Critical count")
    private long criticalCount;
    @Schema(description = "Last pre-operational check date")
    private LocalDate lastPreopDate;
    @Schema(description = "Top issue")
    private String topIssue;
}
