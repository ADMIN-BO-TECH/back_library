package co.com.botech.dto.route;

import jakarta.validation.constraints.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Create or update a route")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRouteRequest {

    @Schema(description = "Route ID (update only)", example = "12")
    private Long routeId;

    @Schema(description = "Vehicle ID", example = "8")
    @NotNull(message = "The vehicleId cannot be null")
    @Min(value = 1, message = "The vehicleId must be a positive number")
    private Long vehicleId;

    @Schema(description = "Allowed: L, M, X, J, V, S, D, comma-separated", example = "L,M,X,J,V")
    @NotNull(message = "The routeDays cannot be null")
    @Pattern(regexp = "^[L,M,X,J,V,S,Dl,m,x,j,v,s,d, ]+$",
            message = "The routeDays can only contain 'L, M, X, J, V, S, D' separated by commas")
    private String routeDays;

    @Schema(description = "Route name", example = "Ruta 12 - Mañana")
    @NotNull(message = "The routeName cannot be null")
    @Size(min = 3, max = 50, message = "The routeName must be between 3 and 50 characters")
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÜÑa-záéíóúüñ0-9 -]+$",
            message = "The routeName can only contain letters, numbers and hyphens")
    private String routeName;

    @Schema(description = "Driver employee ID", example = "42")
    @NotNull(message = "The operatorId cannot be null")
    @Min(value = 1, message = "The operatorId must be a positive integer")
    private Long operatorId;

    @Schema(description = "Assistant employee ID", example = "43")
    @NotNull(message = "The assistantId cannot be null")
    @Min(value = 1, message = "The assistantId must be a positive integer")
    private Long assistantId;

    @Schema(description = "Start time (hh:mm AM/PM)", example = "06:00 AM")
    @NotNull(message = "The startTime cannot be null")
    @Pattern(regexp = "^(0?[1-9]|1[0-2]):[0-5]\\d [APap][Mm]$",
            message = "The startTime must be in format hh:mm AM/PM (e.g. 3:20 PM)")
    private String startTime;

    @Schema(description = "End time (hh:mm AM/PM)", example = "07:30 AM")
    @NotNull(message = "The endTime cannot be null")
    @Pattern(regexp = "^(0?[1-9]|1[0-2]):[0-5]\\d [APap][Mm]$",
            message = "The endTime must be in format hh:mm AM/PM (e.g. 3:20 PM)")
    private String endTime;

    @Schema(description = "Route type; exact RECOGIDA means pickup, other values mean drop-off", example = "RECOGIDA")
    @Size(min = 3, max = 50, message = "The routeType must be between 3 and 50 characters")
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÜÑa-záéíóúüñ -]+$",
            message = "The routeType can only contain letters")
    private String routeType;

    @Schema(description = "Whether to allow schedule conflicts", example = "false")
    private boolean overlapConflicts;
}