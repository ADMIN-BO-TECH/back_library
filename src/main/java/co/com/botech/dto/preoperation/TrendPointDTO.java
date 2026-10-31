package co.com.botech.dto.preoperation;

import lombok.*;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Trend point")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class TrendPointDTO {
    @Schema(description = "Date")
    private LocalDate date;
    @Schema(description = "Total pre-operational checks")
    private long totalPreops;
    @Schema(description = "Pre-operational checks with issues")
    private long preopsWithIssues;
    @Schema(description = "Pre-operational checks with critical issues")
    private long preopsWithCritical;
    @Schema(description = "Issue rate")
    private double issueRate;
    @Schema(description = "Critical rate")
    private double criticalRate;
}
