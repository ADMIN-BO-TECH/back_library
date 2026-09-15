package co.com.botech.dto.route;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RouteStudentResponse {
    private String studentId;
    private String studentSchoolId;
    private String fullName;
    private String grade;
    private boolean inVehicle;
}