package co.com.botech.dto.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Route information")
@Getter
@Setter
@AllArgsConstructor
public class RouteInformation {

    @Schema(description = "Route ID")
    private final String routeId;
    @Schema(description = "Route name")
    private final String routeName;
    @Schema(description = "Vehicle ID")
    private final String vehicleId;
    @Schema(description = "Vehicle plate")
    private final String vehiclePlate;
    @Schema(description = "Fleet number")
    private final String fleetNumber;
    @Schema(description = "Assistant ID")
    private final String assistantId;
    @Schema(description = "Assistant name")
    private final String assistantName;
    @Schema(description = "Operator ID")
    private final String operatorId;
    @Schema(description = "Operator name")
    private final String operatorName;
    @Schema(description = "Route days")
    private final String routeDays;
    @Schema(description = "Start time")
    private final String startTime;
    @Schema(description = "End time")
    private final String endTime;
    @Schema(description = "Route type")
    private final String routeType;
    @Schema(description = "Status")
    private final Boolean status;
    @Schema(description = "School name")
    private final String schoolName;
}