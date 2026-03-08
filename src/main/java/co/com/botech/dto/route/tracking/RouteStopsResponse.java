package co.com.botech.dto.route.tracking;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteStopsResponse {
    private RouteStopsData route;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RouteStopsData {
        private Long id;
        private String name;
        private String fleetNumber;
        private String vehicleRfidTag;
        private List<StopDtoTracking> stops;
    }
}