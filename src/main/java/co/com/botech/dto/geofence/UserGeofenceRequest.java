package co.com.botech.dto.geofence;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserGeofenceRequest {
    private String name;
    private Double latitude;
    private Double longitude;
    private Double radius;
}