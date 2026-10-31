package co.com.botech.dto.route.tracking;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Family student assigned to a route")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyRouteStudentDto {
    @Schema(description = "Student record ID (student_record_id)", example = "1024")
    private Long studentRecordId;
    @Schema(description = "Student school ID (student_id)", example = "20231045")
    private Long studentId;
    @Schema(description = "Last names", example = "Pérez")
    private String lastname;
    @Schema(description = "Student's first names", example = "Juan")
    private String name;
    @Schema(description = "Student grade", example = "5A")
    private String course;
    @Schema(description = "Student's assigned route")
    private FamilyRoutesResponse.RouteDto route;
}
