package co.com.botech.dto.preoperation;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class VehicleOverviewResponse {
    private Long vehicleId;
    private String plateNumber;
    private String fleetNumber;
    private String colegioName;
    private Integer lastKilometrage;
    private LocalDate lastPreopDate;
    private String lastOperator;
    private long totalPreops;
    private long preopsWithIssues;
    private long preopsWithCritical;
    private double issueRate;
    private double criticalRate;
    private String topExternalIssue;
    private String topInternalIssue;
    private List<String> missingKitItems;
    private List<String> expiredDocuments;
}
