package co.com.botech.dto.employeeAttendance;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Worked hours Excel request")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkedHoursExcelRequest {

    @Schema(description = "Start date (yyyy-MM-dd)", example = "2026-10-01")
    @NotNull
    private LocalDate startDate;

    @Schema(description = "End date (yyyy-MM-dd)", example = "2026-10-31")
    @NotNull
    private LocalDate endDate;

    @Schema(description = "Employee IDs", example = "[42, 43]")
    @NotEmpty
    private List<Long> employeeIds;
}