package co.com.botech.dto.route.tracking;

import lombok.*;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Family student routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyRoutesResponse {
    @Schema(description = "Students")
    private List<FamilyRouteStudentDto> students;

    @Schema(description = "Route with vehicle and layout")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RouteDto {
        @Schema(description = "Route ID", example = "12")
        private Long id;
        @Schema(description = "Route name", example = "Ruta 12 - Mañana")
        private String name;
        @Schema(description = "Vehicle fleet number", example = "12")
        private String fleetNumber;
        @Schema(description = "Vehicle RFID tag", example = "E2003412B802099")
        private String vehicleRfidTag;
        @Schema(description = "Encoded polyline for the full route", example = "a~l~Fjk~uOwHJy@P")
        private String totalEncryptedPolyline;
    }
}