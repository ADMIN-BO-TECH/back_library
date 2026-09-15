package co.com.botech.dto.attendance;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RouteExitAllResponse {
    private boolean success;
    private int totalInVehicle;
    private int successfulExits;
    private List<String> failedStudentSchoolIds;
}
