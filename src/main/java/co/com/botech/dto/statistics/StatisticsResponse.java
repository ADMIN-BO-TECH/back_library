package co.com.botech.dto.statistics;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsResponse {
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
    private Long entryTotalRegisters;
    private Long exitTotalRegisters;
    private Long entryExternalRegisters;
    private Long exitExternalRegisters;
    private Long entryGeneralRegisters;
    private Long exitGeneralRegisters;
    private Long totalEmployees;
    private Long presentEmployees;
    private Long presentExternal;
    private Long presentParents;
    private List<GradeStatisticsResponse> grades;
}
