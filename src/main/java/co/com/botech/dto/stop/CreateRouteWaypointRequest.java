package co.com.botech.dto.stop;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateRouteWaypointRequest {

    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "Los nombres de paradas no puede contener caracteres especiales")
    private String name;
    @Min(value = 1, message = "El id del recorrido debe ser un número positivo")
    private Long routeId;

    @Min(value = 1, message = "El orden de parada debe ser un número positivo")
    private int stopOrder;

    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La dirección no puede contener caracteres especiales")
    private String address;

    private double latitude;

    private double longitude;
}