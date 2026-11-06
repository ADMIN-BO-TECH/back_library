package co.com.botech.dto.geofence;

import lombok.*;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User geofence")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserGeofenceResponse {
    @Schema(description = "Geofence ID", example = "9")
    private Long id;
    @Schema(description = "Geofence name", example = "Casa")
    private String name;
    @Schema(description = "Latitude", example = "4.7110")
    private Double latitude;
    @Schema(description = "Longitude", example = "-74.0721")
    private Double longitude;
    @Schema(description = "Geofence radius in meters", example = "150")
    private Double radius;
    @Schema(description = "Created date and time", example = "2026-10-20T07:30:00")
    private LocalDateTime createdAt;
}
