package co.com.botech.dto.routeWaypoint;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Route waypoint update")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateRouteWaypointRequest {
    @Schema(description = "Waypoint ID", example = "88")
    @NonNull
    @Min(value = 1, message = "Los ID's deben ser positivos y enteros")
    private Long id;
    @Schema(description = "Waypoint name", example = "Glorieta calle 100")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "Los nombres de paradas no puede contener caracteres especiales")
    private String name;
    @Schema(description = "Stop order in the route", example = "3")
    @Min(value = 1, message = "El orden de parada debe ser un número positivo")
    private int stopOrder;

    @Schema(description = "Waypoint order within the stop", example = "1")
    @Min(value = 1, message = "El orden de parada especifico debe ser un número positivo")
    private int specificOrder;

    @Schema(description = "Address", example = "Calle 106 # 18-40, Bogotá")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La dirección no puede contener caracteres especiales")
    private String address;

    @Schema(description = "Latitude", example = "4.7110")
    private double latitude;

    @Schema(description = "Longitude", example = "-74.0721")
    private double longitude;
}