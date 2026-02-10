package co.com.botech.dto.route.tracking;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyRouteStudentDto {
    private String lastname;

    private String name;

    private String course;

    private FamilyRoutesResponse.RouteDto route;
}
