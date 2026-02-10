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
        private String id;
        private String name;
        private Integer number;
        private List<RouteStudentDto> students;
    }
}
