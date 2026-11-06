package co.com.botech.dto.routeWaypoint;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "New route waypoint")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateRouteWaypointRequest {

    @Schema(description = "Waypoint name", example = "Glorieta calle 100")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "Los nombres de paradas no puede contener caracteres especiales")
    private String name;
    @Schema(description = "Route ID", example = "12")
    @Min(value = 1, message = "El id del recorrido debe ser un número positivo")
    private Long routeId;

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