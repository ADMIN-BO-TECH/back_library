package co.com.botech.dto.statistics;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasicStatisticsResponse {
    private float totalPercentageOfPresentStudents;
    private Long totalStudents;
    private Long presentStudents;
    private Long absentStudents;
    private Long entryStudentRegisters;
    private Long exitStudentRegisters;
    private Long entryEmployeeRegisters;
    private Long exitEmployeeRegisters;
    private Long entryParentRegisters;
    private Long exitParentRegisters;
    private Long entryExternalRegisters;
    private Long exitExternalRegisters;
    private Long entryTotalRegisters;
    private Long exitTotalRegisters;
    private Long totalEmployees;
    private Long presentEmployees;
    private Long presentExternal;
    private Long presentParents;
    private List<GradeBasicStatisticsResponse> grades;
}
