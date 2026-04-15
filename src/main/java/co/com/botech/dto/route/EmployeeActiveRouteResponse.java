package co.com.botech.dto.route;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeActiveRouteResponse {
    private String routeId;
    private String routeName;
    private Long idVehiculo;
    private String routeDays;
    private String startTime;
    private String endTime;
    private String routeType;
    private String schoolName;
}
