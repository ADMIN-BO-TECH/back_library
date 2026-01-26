package co.com.botech.dto.employee;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeWeekRequest {
    @NotNull
    private Long employeeId;
    @NotNull
    private LocalDate requiredDate;
}
