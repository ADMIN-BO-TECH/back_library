package co.com.botech.dto.route;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeekRouteResponse {
    private String routeId;
    private String routeName;
    private String vehicleId;
    private String plateNumber;
    private String fleetNumber;
    private String assistantId;
    private String assistantName;
    private String operatorId;
    private String operatorName;
    private String routeDays;
    private String startTime;
    private String endTime;
    private String routeType;
    private String status;
    private String schoolName;
}
