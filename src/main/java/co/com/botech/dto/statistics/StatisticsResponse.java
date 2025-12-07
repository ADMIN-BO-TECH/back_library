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
    private Long entryRegisters;
    private Long exitRegisters;
    private Long totalEmployees;
    private Long presentEmployees;
    private Long presentExternal;
    private Long entryExternalRegisters;
    private Long exitExternalRegisters;
    private Long presentParents;
    private List<GradeStatisticsResponse> grades;
}
