package co.com.botech.dto.employee;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Employee work week search")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeWeekRequest {
    @Schema(description = "Employee ID", example = "42")
    @NotNull
    private Long employeeId;
    @Schema(description = "Date within the week (yyyy-MM-dd)", example = "2026-10-20")
    @NotNull
    private LocalDate requiredDate;
}
