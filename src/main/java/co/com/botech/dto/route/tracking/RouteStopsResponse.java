package co.com.botech.dto.route.tracking;

import lombok.*;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Stops of a route")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteStopsResponse {
    @Schema(description = "Route with stops")
    private RouteStopsData route;

    @Schema(description = "Route with stops")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RouteStopsData {
        @Schema(description = "Route ID", example = "12")
        private Long id;
        @Schema(description = "Route name", example = "Ruta 12 - Mañana")
        private String name;
        @Schema(description = "Vehicle fleet number", example = "12")
        private String fleetNumber;
        @Schema(description = "Vehicle RFID tag", example = "E2003412B802099")
        private String vehicleRfidTag;
        @Schema(description = "Route stops")
        private List<StopDtoTracking> stops;
    }
}