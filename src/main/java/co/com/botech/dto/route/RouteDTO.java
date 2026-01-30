package co.com.botech.dto.route;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RouteDTO {
    private Long id;
    private String routeName;
    private String routeDays;
    private Long vehicleId;
    private boolean status;
    private int assistantId;
    private int operatorId;
    private String startTime;
    private String endTime;
    private String routeType;
    private String schoolName;
    private boolean overlapConflicts;
}
