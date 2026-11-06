package co.com.botech.dto.statistics;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Advanced statistics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvancedStatisticsResponse {
    @Schema(description = "General statistics")
    private BasicStatisticsResponse generalStatistics;
    @Schema(description = "Card use statistics")
    private CardUseStatistics cardUseStatistics;
}
