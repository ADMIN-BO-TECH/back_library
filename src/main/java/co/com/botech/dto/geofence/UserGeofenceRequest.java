package co.com.botech.dto.geofence;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Create or update user geofence")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserGeofenceRequest {
    @Schema(description = "Geofence name", example = "Casa")
    private String name;
    @Schema(description = "Latitude", example = "4.7110")
    private Double latitude;
    @Schema(description = "Longitude", example = "-74.0721")
    private Double longitude;
    @Schema(description = "Geofence radius in meters", example = "150")
    private Double radius;
}