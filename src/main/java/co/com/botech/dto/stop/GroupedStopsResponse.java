package co.com.botech.dto.stop;

import co.com.botech.util.utilObjects.GroupedStopRelationInterface;
import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Grouped stops")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GroupedStopsResponse {
    @Schema(description = "Final order")
    private int finalOrder;
    @Schema(description = "Latitude")
    private double latitude;
    @Schema(description = "Longitude")
    private double longitude;
    @Schema(description = "Address")
    private String address;
    @Schema(description = "Time")
    private String time;
    @Schema(description = "Stop original order")
    private int stopOriginalOrder;
    @Schema(description = "Is an auxiliary stop")
    private boolean isRouteWayPoint;
    @Schema(description = "People linked to the stop")
    private List<GroupedStopRelationInterface> relatedEntities;
}
