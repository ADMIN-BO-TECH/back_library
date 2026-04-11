package co.com.botech.dto.routeWaypoint;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateRouteWaypointRequest {
    @NonNull
    @Min(value = 1, message = "Los ID's deben ser positivos y enteros")
    private Long id;
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "Los nombres de paradas no puede contener caracteres especiales")
    private String name;
    @Min(value = 1, message = "El orden de parada debe ser un número positivo")
    private int stopOrder;

    @Min(value = 1, message = "El orden de parada especifico debe ser un número positivo")
    private int specificOrder;

    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La dirección no puede contener caracteres especiales")
    private String address;

    private double latitude;

    private double longitude;
}