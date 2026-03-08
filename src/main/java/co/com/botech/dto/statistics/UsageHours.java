package co.com.botech.dto.statistics;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsageHours {
    private List<HoursSpecificStatistics> topHours;
    private List<HoursSpecificStatistics> lowestHours;
}
