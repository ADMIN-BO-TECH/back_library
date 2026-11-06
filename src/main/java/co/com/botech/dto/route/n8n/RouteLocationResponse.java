package co.com.botech.dto.route.n8n;

import co.com.botech.dto.stop.n8n.StopBasicInfoResponse;
import co.com.botech.dto.vehicle.n8n.VehicleBasicInfoResponse;
import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Route location")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteLocationResponse {
    @Schema(description = "Route ID")
    private Long routeId;
    @Schema(description = "Name")
    private String name;
    @Schema(description = "Status")
    private boolean status;
    @Schema(description = "Vehicle")
    private VehicleBasicInfoResponse vehicle;
    @Schema(description = "Location")
    private LocationResponse location;
    @Schema(description = "Stops")
    private List<StopBasicInfoResponse> stops;
}
