package co.com.botech.dto.routeWaypoint;

import co.com.botech.util.utilObjects.GroupedStopRelationInterface;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RouteWaypointGroupedResponse implements GroupedStopRelationInterface {
    private Long relationId;
    private String name;
    private int stopOrder;
}
