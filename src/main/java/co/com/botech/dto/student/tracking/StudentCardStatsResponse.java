package co.com.botech.dto.student.tracking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCardStatsResponse {

    private long totalUses;
    private Map<String, Long> byType;
    private long correctUses;
    private long incorrectUses;
    private List<DailySummaryEntry> dailySummary;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DailySummaryEntry {
        private String date;
        private int expectedUses;
        private int actualUses;
        private String status;
    }
}

