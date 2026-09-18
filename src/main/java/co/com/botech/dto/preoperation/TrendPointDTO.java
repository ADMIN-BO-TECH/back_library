package co.com.botech.dto.preoperation;

import lombok.*;

import java.time.LocalDate;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class TrendPointDTO {
    private LocalDate date;
    private long totalPreops;
    private long preopsWithIssues;
    private long preopsWithCritical;
    private double issueRate;
    private double criticalRate;
}
