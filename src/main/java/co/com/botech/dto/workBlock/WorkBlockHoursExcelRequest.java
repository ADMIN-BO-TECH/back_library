package co.com.botech.dto.workBlock;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkBlockHoursExcelRequest {
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    // Vacio o null = todos los empleados del cliente (solo los que tienen bloques en el periodo).
    private List<Long> employeeIds;
    // null = false. Los bloques abiertos (sin hora de salida) se listan, pero nunca suman horas.
    private Boolean includeIncomplete;
    // null = true. Agrega una fila TOTAL DÍA despues de los bloques de cada dia.
    private Boolean includeDailyTotals;
}
