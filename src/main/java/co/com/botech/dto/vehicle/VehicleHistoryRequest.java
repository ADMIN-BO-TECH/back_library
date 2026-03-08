package co.com.botech.dto.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleHistoryRequest {

    @NotBlank(message = "El rfidTag es obligatorio")
    private String rfidTag;

    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La fecha de inicio contiene caracteres inválidos")
    private String startDate;

    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La hora de inicio contiene caracteres inválidos")
    private String startTime;

    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La fecha de fin contiene caracteres inválidos")
    private String endDate;

    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La hora de fin contiene caracteres inválidos")
    private String endTime;

    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La velocidad Míninma contiene caracteres inválidos")
    private String minSpeed;

}
