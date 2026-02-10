package co.com.botech.dto.vehicle;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpeedingExcelObject {

    @JsonProperty("Fecha")
    @NotNull
    @Pattern(regexp="^\\d{4}-\\d{2}-\\d{2}$", message="La fecha debe tener formato YYYY-MM-DD")
    private String date;

    @JsonProperty("Hora")
    @NotNull
    @Pattern(regexp="^([01]\\d|2[0-3]):[0-5]\\d(:[0-5]\\d)?$", message="La hora debe tener formato HH:mm o HH:mm:ss")
    private String time;

    @JsonProperty("Dirección")
    @NotNull
    @Pattern(regexp="^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ#.,()\\-_/\\\\]+$", message="La dirección contiene caracteres inválidos")
    private String address;

    @JsonProperty("Velocidad Registrada")
    @PositiveOrZero(message="La velocidad registrada no puede ser negativa")
    private double speed;

    @JsonProperty("Límite de Velocidad")
    @Positive(message="El límite de velocidad debe ser mayor que cero")
    private double speedLimit;
}