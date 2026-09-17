package co.com.botech.dto.preoperation;

import lombok.*;

import java.time.LocalDate;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class VehicleRankingItemDTO {
    private Long vehicleId;
    private String plateNumber;
    private String fleetNumber;
    private String colegioName;
    private long totalPreops;
    private long preopsWithIssues;
    private double issueRate;
    private long criticalCount;
    private LocalDate lastPreopDate;
    private String topIssue;
}
