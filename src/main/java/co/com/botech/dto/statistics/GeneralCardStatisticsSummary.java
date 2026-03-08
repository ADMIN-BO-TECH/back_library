package co.com.botech.dto.statistics;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneralCardStatisticsSummary {
    private LocalDateTime dateTime;
    private Long totalStudents;
    private Long totalEmployees;
    private Long totalActiveParents;
    private Long totalActiveExternal;
    private Long totalRegistersToday;
    private Long totalUsersWithRegistersToday;
    private Double studentUsagePercentage;
    private Double averageRegistersPerPerson;
}
