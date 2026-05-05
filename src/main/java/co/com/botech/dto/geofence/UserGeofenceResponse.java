package co.com.botech.dto.geofence;

import lombok.*;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserGeofenceResponse {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Double radius;
    private LocalDateTime createdAt;
}
