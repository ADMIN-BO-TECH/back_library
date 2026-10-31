package co.com.botech.dto.workBlock;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Filters to export work block hours to Excel")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkBlockHoursExcelRequest {
    @Schema(description = "Period start date (yyyy-MM-dd)", example = "2026-10-01")
    @NotNull
    private LocalDate startDate;
    @Schema(description = "Period end date (yyyy-MM-dd)", example = "2026-10-31")
    @NotNull
    private LocalDate endDate;
    // Vacio o null = todos los empleados del cliente (solo los que tienen bloques en el periodo).
    @Schema(description = "Employee IDs. Empty or null includes everyone with blocks in the period", example = "[42, 57]")
    private List<Long> employeeIds;
    // null = false. Los bloques abiertos (sin hora de salida) se listan, pero nunca suman horas.
    @Schema(description = "Includes open blocks without exit time. They add no hours. Default false", example = "false")
    private Boolean includeIncomplete;
    // null = true. Agrega una fila TOTAL DÍA despues de los bloques de cada dia.
    @Schema(description = "Adds a daily total row. Default true", example = "true")
    private Boolean includeDailyTotals;
}
