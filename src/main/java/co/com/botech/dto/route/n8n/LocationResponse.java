package co.com.botech.dto.route.n8n;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationResponse {
    private double lat;
    private double lng;
    private String timestamp;
    private Long ageSeconds;
    private String status;
}
