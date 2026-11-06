package co.com.botech.dto.route.tracking;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Route data for the public tracking link")
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class RouteLinkData {
    @Schema(description = "Route ID", example = "12")
    private Long id;
    @Schema(description = "Route name", example = "Ruta 12 - Mañana")
    private String name;
    @Schema(description = "Route start time", example = "06:00 AM")
    private String routeStartTime;
    @Schema(description = "Route end time", example = "07:30 AM")
    private String routeEndTime;
    @Schema(description = "Vehicle fleet number", example = "12")
    private String fleetNumber;
    @Schema(description = "Vehicle RFID tag", example = "E2003412B802099")
    private String vehicleRfidTag;
    @Schema(description = "Encoded polyline for the full route", example = "a~l~Fjk~uOwHJy@P")
    private String totalEncryptedPolyline;
}
