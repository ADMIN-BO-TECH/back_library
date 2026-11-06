package co.com.botech.dto.student.n8n;

import co.com.botech.dto.route.n8n.RouteSummary;
import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student by user with active route")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentByUserWithActiveRouteResponse {
    @Schema(description = "Student school ID")
    private Long studentSchoolId;
    @Schema(description = "First name")
    private String firstName;
    @Schema(description = "Last name")
    private String lastName;
    @Schema(description = "School name")
    private String schoolName;
    @Schema(description = "Grade")
    private String grade;
    @Schema(description = "Moving routes")
    private List<RouteSummary> movingRoutes;
    @Schema(description = "Static routes")
    private List<RouteSummary> staticRoutes;
}
