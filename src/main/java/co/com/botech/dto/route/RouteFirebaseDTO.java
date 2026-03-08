package co.com.botech.dto.route;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteFirebaseDTO {
    private String routeId;
    private String routeName;
    private String routeDays;
    private String startTime;
    private String endTime;
    private Boolean status;
    private String routeType;
    private String schoolName;

    private Map<String, Object> vehicle;
    private Map<String, Object> assistant;
    private Map<String, Object> operator;

    private Long syncedAtMillis;
}