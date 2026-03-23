package co.com.botech.dto.student.n8n;

import co.com.botech.dto.route.n8n.ActiveRouteSummary;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentByUserWithActiveRouteResponse {
    private Long studentSchoolId;
    private String firstName;
    private String lastName;
    private String schoolName;
    private String grade;
    private String status;
    private ActiveRouteSummary activeRoute;
}
