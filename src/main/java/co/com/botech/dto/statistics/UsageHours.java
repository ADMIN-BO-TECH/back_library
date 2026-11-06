package co.com.botech.dto.statistics;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Usage hours")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsageHours {
    @Schema(description = "Top hours")
    private List<HoursSpecificStatistics> topHours;
    @Schema(description = "Lowest hours")
    private List<HoursSpecificStatistics> lowestHours;
}
