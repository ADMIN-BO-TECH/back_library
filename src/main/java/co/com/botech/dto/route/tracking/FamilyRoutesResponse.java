package co.com.botech.dto.route.tracking;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyRoutesResponse {
    private List<FamilyRouteStudentDto> students;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RouteDto {
        private Long id;
        private String name;
        private String fleetNumber;
        private String vehicleRfidTag;
        private String totalEncryptedPolyline;
    }
}