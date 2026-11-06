package co.com.botech.dto.route;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "School transport route")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RouteDTO {
    @Schema(description = "Route ID", example = "12")
    private Long id;
    @Schema(description = "Route name", example = "Ruta 12 - Mañana")
    private String routeName;
    @Schema(description = "Allowed: L, M, X, J, V, S, D, comma-separated", example = "L,M,X,J,V")
    private String routeDays;
    @Schema(description = "Vehicle ID", example = "8")
    private Long vehicleId;
    @Schema(description = "Whether the route is active", example = "true")
    private boolean status;
    @Schema(description = "Assistant employee ID", example = "43")
    private Long assistantId;
    @Schema(description = "Driver employee ID", example = "42")
    private Long operatorId;
    @Schema(description = "Start time (hh:mm AM/PM)", example = "06:00 AM")
    private String startTime;
    @Schema(description = "End time (hh:mm AM/PM)", example = "07:30 AM")
    private String endTime;
    @Schema(description = "Route type; exact RECOGIDA means pickup, other values mean drop-off", example = "RECOGIDA")
    private String routeType;
    @Schema(description = "School name", example = "Colegio San José")
    private String schoolName;
    @Schema(description = "Whether to allow schedule conflicts", example = "false")
    private boolean overlapConflicts;
}