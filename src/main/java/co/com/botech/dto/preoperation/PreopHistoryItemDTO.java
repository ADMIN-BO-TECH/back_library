package co.com.botech.dto.preoperation;

import lombok.*;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Pre-operational check history item")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PreopHistoryItemDTO {
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "Date")
    private LocalDate date;
    @Schema(description = "Hour")
    private String hour;
    @Schema(description = "Operator name")
    private String operatorName;
    @Schema(description = "Operator ID")
    private String operatorId;
    @Schema(description = "Plate number")
    private String plateNumber;
    @Schema(description = "Vehicle ID")
    private Long vehicleId;
    @Schema(description = "Mileage")
    private Integer kilometraje;
    @Schema(description = "School name")
    private String colegioName;

    @Schema(description = "Has issues")
    private boolean hasIssues;
    @Schema(description = "Has critical")
    private boolean hasCritical;
    @Schema(description = "Issue count")
    private int issueCount;
}
