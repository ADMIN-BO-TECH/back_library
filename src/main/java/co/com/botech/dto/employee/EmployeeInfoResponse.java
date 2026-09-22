package co.com.botech.dto.employee;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeInfoResponse {
    private Long id;
    private String documentNumber;
    private String name;
    private String position;
}