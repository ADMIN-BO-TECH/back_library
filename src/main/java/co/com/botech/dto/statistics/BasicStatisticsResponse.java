package co.com.botech.dto.statistics;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Basic statistics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasicStatisticsResponse {
    @Schema(description = "Total percentage of present students")
    private float totalPercentageOfPresentStudents;
    @Schema(description = "Total students")
    private Long totalStudents;
    @Schema(description = "Present students")
    private Long presentStudents;
    @Schema(description = "Absent students")
    private Long absentStudents;
    @Schema(description = "Entry student registers")
    private Long entryStudentRegisters;
    @Schema(description = "Exit student registers")
    private Long exitStudentRegisters;
    @Schema(description = "Entry employee registers")
    private Long entryEmployeeRegisters;
    @Schema(description = "Exit employee registers")
    private Long exitEmployeeRegisters;
    @Schema(description = "Entry parent registers")
    private Long entryParentRegisters;
    @Schema(description = "Exit parent registers")
    private Long exitParentRegisters;
    @Schema(description = "Entry external registers")
    private Long entryExternalRegisters;
    @Schema(description = "Exit external registers")
    private Long exitExternalRegisters;
    @Schema(description = "Entry total registers")
    private Long entryTotalRegisters;
    @Schema(description = "Exit total registers")
    private Long exitTotalRegisters;
    @Schema(description = "Total employees")
    private Long totalEmployees;
    @Schema(description = "Present employees")
    private Long presentEmployees;
    @Schema(description = "Present external")
    private Long presentExternal;
    @Schema(description = "Present parents")
    private Long presentParents;
    @Schema(description = "Grades")
    private List<GradeBasicStatisticsResponse> grades;
}
