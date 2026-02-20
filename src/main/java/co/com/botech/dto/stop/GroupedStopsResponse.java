package co.com.botech.dto.stop;

import co.com.botech.util.utilObjects.GroupedStopRelationInterface;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GroupedStopsResponse {
    private int finalOrder;
    private double latitude;
    private double longitude;
    private String address;
    private String time;
    private int stopOriginalOrder;
    private boolean isRouteWayPoint;
    private List<GroupedStopRelationInterface> relatedEntities;
}
