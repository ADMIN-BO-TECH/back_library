package co.com.botech.dto.route.n8n;

import co.com.botech.dto.stop.n8n.StopBasicInfoResponse;
import co.com.botech.dto.vehicle.n8n.VehicleBasicInfoResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteLocationResponse {
    private Long routeId;
    private String name;
    private boolean status;
    private VehicleBasicInfoResponse vehicle;
    private LocationResponse location;
    private List<StopBasicInfoResponse> stops;
}
