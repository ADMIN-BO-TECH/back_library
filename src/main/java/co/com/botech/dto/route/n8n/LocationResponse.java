package co.com.botech.dto.route.n8n;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Location")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationResponse {
    @Schema(description = "Latitude")
    private double lat;
    @Schema(description = "Longitude")
    private double lng;
    @Schema(description = "Date and time")
    private String timestamp;
    @Schema(description = "Location age in seconds")
    private Long ageSeconds;
    @Schema(description = "Status")
    private String status;
}
