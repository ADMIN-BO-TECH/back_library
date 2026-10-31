package co.com.botech.dto.statistics;

import lombok.*;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "General card statistics summary")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneralCardStatisticsSummary {
    @Schema(description = "Date time")
    private LocalDateTime dateTime;
    @Schema(description = "Total students")
    private Long totalStudents;
    @Schema(description = "Total employees")
    private Long totalEmployees;
    @Schema(description = "Total active parents")
    private Long totalActiveParents;
    @Schema(description = "Total active external")
    private Long totalActiveExternal;
    @Schema(description = "Total registers today")
    private Long totalRegistersToday;
    @Schema(description = "Total users with registers today")
    private Long totalUsersWithRegistersToday;
    @Schema(description = "Student usage percentage")
    private Double studentUsagePercentage;
    @Schema(description = "Average registers per person")
    private Double averageRegistersPerPerson;
}
