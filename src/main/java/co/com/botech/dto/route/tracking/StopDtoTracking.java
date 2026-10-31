package co.com.botech.dto.route.tracking;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Route stop for app tracking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StopDtoTracking {
    @Schema(description = "Stop ID", example = "301")
    private Long id;
    @Schema(description = "Stop order in the route", example = "3")
    private Integer order;
    @Schema(description = "Stop type ID: 1 = student stop, 4 = school", example = "1")
    private Long type;
    @Schema(description = "Estimated stop time", example = "06:25 AM")
    private String time;
    @Schema(description = "Address", example = "Calle 106 # 18-40, Bogotá")
    private String address;
    @Schema(description = "Stop location")
    private LocationDto location;
    @Schema(description = "Allowed: ACTIVE, INACTIVE", example = "ACTIVE")
    private String status;

    @Schema(description = "Coordinates")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LocationDto {
        @Schema(description = "Latitude", example = "4.7110")
        private Double lat;
        @Schema(description = "Longitude", example = "-74.0721")
        private Double lng;
    }
}