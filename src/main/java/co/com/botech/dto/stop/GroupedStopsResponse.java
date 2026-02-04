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
    private double latitude;
    private double longitude;
    private String address;
    private int time;
    private int stopOrder;
    private List<GroupedStopRelationInterface> relatedEntities;
}
