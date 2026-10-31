package co.com.botech.dto.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicle position history search")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleHistoryRequest {

    @Schema(description = "Vehicle RFID tag", example = "E2003412B802099")
    @NotBlank(message = "El rfidTag es obligatorio")
    private String rfidTag;

    @Schema(description = "Start date (yyyy-MM-dd)", example = "2026-10-01")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La fecha de inicio contiene caracteres inválidos")
    private String startDate;

    @Schema(description = "Start time (HH:mm)", example = "06:00")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La hora de inicio contiene caracteres inválidos")
    private String startTime;

    @Schema(description = "End date (yyyy-MM-dd)", example = "2026-10-31")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La fecha de fin contiene caracteres inválidos")
    private String endDate;

    @Schema(description = "End time (HH:mm)", example = "08:00")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La hora de fin contiene caracteres inválidos")
    private String endTime;

    @Schema(description = "Minimum speed in km/h", example = "60")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La velocidad Míninma contiene caracteres inválidos")
    private String minSpeed;

}
