package co.com.botech.dto.preoperation;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicle overview")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class VehicleOverviewResponse {
    @Schema(description = "Vehicle ID")
    private Long vehicleId;
    @Schema(description = "Plate number")
    private String plateNumber;
    @Schema(description = "Fleet number")
    private String fleetNumber;
    @Schema(description = "School name")
    private String colegioName;

    @Schema(description = "Last kilometrage")
    private Integer lastKilometrage;
    @Schema(description = "Last pre-operational check date")
    private LocalDate lastPreopDate;
    @Schema(description = "Last operator")
    private String lastOperator;
    @Schema(description = "Total pre-operational checks")
    private long totalPreops;
    @Schema(description = "Pre-operational checks with issues")
    private long preopsWithIssues;
    @Schema(description = "Pre-operational checks with critical issues")
    private long preopsWithCritical;
    @Schema(description = "Issue rate")
    private double issueRate;
    @Schema(description = "Critical rate")
    private double criticalRate;
    @Schema(description = "Top external issue")
    private String topExternalIssue;
    @Schema(description = "Top internal issue")
    private String topInternalIssue;
    @Schema(description = "Missing kit items")
    private List<String> missingKitItems;
    @Schema(description = "Expired documents")
    private List<String> expiredDocuments;
}
