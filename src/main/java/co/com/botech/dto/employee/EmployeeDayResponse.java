package co.com.botech.dto.employee;

import co.com.botech.dto.route.WeekRouteResponse;
import co.com.botech.dto.vehicle.AttendancePointDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Employee day")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDayResponse {
    @Schema(description = "Register day")
    private String registerDay;

    @Schema(description = "Routes")
    private List<WeekRouteResponse> routes;

    @Schema(description = "Attendance points")
    private List<AttendancePointDTO> attendancePoints;

    @Schema(description = "Hours register")
    private HoursRegisterDTO hoursRegister;
}
