package co.com.botech.dto.statistics;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeBasicStatisticsResponse {
    private String gradeLevel;
    private float attendancePercentage;
    private Long totalStudents;
    private Long presentStudents;
    private Long absentStudents;
}
