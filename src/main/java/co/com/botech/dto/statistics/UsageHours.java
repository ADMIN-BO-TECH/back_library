package co.com.botech.dto.statistics;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsageHours {
    private HoursSpecificStatistics topHour;
    private HoursSpecificStatistics lowestHour;
}
