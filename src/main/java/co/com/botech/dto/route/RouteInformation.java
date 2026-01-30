package co.com.botech.dto.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RouteInformation {

    private final String routeId;
    private final String routeName;
    private final String vehicleId;
    private final String vehiclePlate;
    private final String fleetNumber;
    private final String assistantId;
    private final String assistantName;
    private final String operatorId;
    private final String operatorName;
    private final String routeDays;
    private final String startTime;
    private final String endTime;
    private final String routeType;
    private final Boolean status;
    private final String schoolName;
}