package co.com.botech.dto.statistics;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Advanced statistics date range")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvancedStatisticsRequest {
    @Schema(description = "Start date (yyyy-MM-dd)", example = "2026-10-01")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La fecha de inicio contiene caracteres inválidos")
    private String startDate;
    @Schema(description = "End date (yyyy-MM-dd)", example = "2026-10-31")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La fecha de fin contiene caracteres inválidos")
    private String endDate;
}
