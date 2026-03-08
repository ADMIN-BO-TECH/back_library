package co.com.botech.dto.statistics;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvancedStatisticsResponse {
    private BasicStatisticsResponse generalStatistics;
    private CardUseStatistics cardUseStatistics;
}
