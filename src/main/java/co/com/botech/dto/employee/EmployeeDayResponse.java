package co.com.botech.dto.employee;

import co.com.botech.dto.route.WeekRouteResponse;
import co.com.botech.dto.vehicle.AttendancePointDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDayResponse {
    private String registerDay;

    private List<WeekRouteResponse> routes;

    private List<AttendancePointDTO> attendancePoints;

    private HoursRegisterDTO hoursRegister;
}
