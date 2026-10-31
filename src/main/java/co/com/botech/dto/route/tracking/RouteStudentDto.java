package co.com.botech.dto.route.tracking;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student or employee assigned to a route stop")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteStudentDto {
    @Schema(description = "Student record ID (student_record_id)", example = "1024")
    private Long studentRecordId;
    @Schema(description = "Student school ID (student_id)", example = "20231045")
    private Long studentId;
    @Schema(description = "School employee ID, if the person is an employee", example = "61")
    private Long schoolEmployeeId;
    @Schema(description = "First names", example = "Juan")
    private String name;
    @Schema(description = "Last names", example = "Pérez")
    private String lastName;
    @Schema(description = "Family code", example = "12345678")
    private String familyCode;
    @Schema(description = "Assigned stop")
    private StopDtoTracking stop;
}