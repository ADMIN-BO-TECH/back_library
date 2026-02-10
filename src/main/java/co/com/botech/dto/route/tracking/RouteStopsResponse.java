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
        private String id;
        private String name;
        private Integer number;
        private List<StopDtoTracking> stops;
    }
}
