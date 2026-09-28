package co.com.botech.dto.employeeAttendance;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Builder
public class EmployeeAttendanceResponse {
    private Long id;
    private OffsetDateTime attendanceTime;
    private Double latitude;
    private Double longitude;
}
