package co.com.botech.dto.geofence;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Bus GPS position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusLocationEvent {

    @Schema(description = "Vehicle RFID tag", example = "E2003412B802099")
    private String rfidTag;
    @Schema(description = "Latitude", example = "4.7110")
    private Double latitude;
    @Schema(description = "Longitude", example = "-74.0721")
    private Double longitude;
}
