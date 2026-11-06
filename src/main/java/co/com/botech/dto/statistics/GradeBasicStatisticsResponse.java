package co.com.botech.dto.statistics;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Grade basic statistics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeBasicStatisticsResponse {
    @Schema(description = "Grade level")
    private String gradeLevel;
    @Schema(description = "Attendance percentage")
    private float attendancePercentage;
    @Schema(description = "Total students")
    private Long totalStudents;
    @Schema(description = "Present students")
    private Long presentStudents;
    @Schema(description = "Absent students")
    private Long absentStudents;
}
