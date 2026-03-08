package co.com.botech.dto.statistics;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardUseStatistics {
    private GeneralCardStatisticsSummary generalSummary;
    private List<TopStudentUsage> topStudentUsageList;
    private List<GradeUsage> gradeUsagesList;
    private List<UserTypeUsage> userTypeUsageList;
    private UsageHours usageTopAndLowestHours;
    private List<LastWeekUsage> lastWeekUsage;
}
