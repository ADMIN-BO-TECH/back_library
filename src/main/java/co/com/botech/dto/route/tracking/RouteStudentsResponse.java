package co.com.botech.dto.route.tracking;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteStudentsResponse {
    private RouteStudentsData route;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RouteStudentsData {
        private Long id;
        private String name;
        private String fleetNumber;
        private String vehicleRfidTag;
        private List<RouteStudentDto> students;
    }
}