package co.com.botech.dto.route.tracking;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyRoutesResponse {
    private RouteDto route;
    private List<FamilyRouteStudentDto> students;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RouteDto {
        private String id;
        private String name;
        private Integer number;
        private String totalEncryptedPolyline;
    }
}