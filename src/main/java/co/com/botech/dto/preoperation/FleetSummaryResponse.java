package co.com.botech.dto.preoperation;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class FleetSummaryResponse {
    private PreopPeriodDTO period;
    private long totalPreops;
    private long preopsWithIssues;
    private long preopsWithCritical;
    private long preopsClean;
    private long totalVehicles;
    private long vehiclesWithIssues;
    private long vehiclesWithCritical;
    private double issueRate;
    private double criticalRate;
    private double cleanRate;
}
