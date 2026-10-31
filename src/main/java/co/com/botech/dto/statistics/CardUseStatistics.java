package co.com.botech.dto.statistics;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Card use statistics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardUseStatistics {
    @Schema(description = "General summary")
    private GeneralCardStatisticsSummary generalSummary;
    @Schema(description = "Top student usage list")
    private List<TopStudentUsage> topStudentUsageList;
    @Schema(description = "Grade usages list")
    private List<GradeUsage> gradeUsagesList;
    @Schema(description = "User type usage list")
    private List<UserTypeUsage> userTypeUsageList;
    @Schema(description = "Usage top and lowest hours")
    private UsageHours usageTopAndLowestHours;
    @Schema(description = "Last week usage")
    private List<LastWeekUsage> lastWeekUsage;
}
