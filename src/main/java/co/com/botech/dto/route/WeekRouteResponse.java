package co.com.botech.dto.route;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Week route")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeekRouteResponse {
    @Schema(description = "Route ID")
    private String routeId;
    @Schema(description = "Route name")
    private String routeName;
    @Schema(description = "Vehicle ID")
    private String vehicleId;
    @Schema(description = "Plate number")
    private String plateNumber;
    @Schema(description = "Fleet number")
    private String fleetNumber;
    @Schema(description = "Assistant ID")
    private String assistantId;
    @Schema(description = "Assistant name")
    private String assistantName;
    @Schema(description = "Operator ID")
    private String operatorId;
    @Schema(description = "Operator name")
    private String operatorName;
    @Schema(description = "Route days")
    private String routeDays;
    @Schema(description = "Start time")
    private String startTime;
    @Schema(description = "End time")
    private String endTime;
    @Schema(description = "Route type")
    private String routeType;
    @Schema(description = "Status")
    private String status;
    @Schema(description = "School name")
    private String schoolName;
}
