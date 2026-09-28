package co.com.botech.dto.employeeAttendance;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class EmployeeAttendanceHistoryResponse {
    private List<EmployeeAttendanceResponse> items;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
