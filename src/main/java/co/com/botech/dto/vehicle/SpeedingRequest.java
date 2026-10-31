package co.com.botech.dto.vehicle;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicle speeding search")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpeedingRequest {
    @Schema(description = "Vehicle fleet number", example = "12")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El numFlota contiene caracteres inválidos")
    private String fleetNumber;

    @Schema(description = "Start date (yyyy-MM-dd)", example = "2026-10-01")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La fecha de inicio contiene caracteres inválidos")
    private String startDate;

    @Schema(description = "Start time (HH:mm)", example = "06:00")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La hora de inicio contiene caracteres inválidos")
    private String startTime;

    @Schema(description = "End date (yyyy-MM-dd)", example = "2026-10-31")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La fecha de fin contiene caracteres inválidos")
    private String endDate;

    @Schema(description = "End time (HH:mm)", example = "18:00")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La hora de fin contiene caracteres inválidos")
    private String endTime;
}
