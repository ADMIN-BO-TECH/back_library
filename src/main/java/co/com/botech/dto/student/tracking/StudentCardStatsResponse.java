package co.com.botech.dto.student.tracking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student card usage stats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCardStatsResponse {

    @Schema(description = "Total card reads in the period", example = "40")
    private long totalUses;
    @Schema(description = "Reads by record type", example = "{\"Subida Bus\": 20, \"Bajada Bus\": 20}")
    private Map<String, Long> byType;
    @Schema(description = "Correct reads", example = "38")
    private long correctUses;
    @Schema(description = "Incorrect reads", example = "2")
    private long incorrectUses;
    @Schema(description = "Daily usage summary")
    private List<DailySummaryEntry> dailySummary;

    @Schema(description = "Daily card usage summary")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DailySummaryEntry {
        @Schema(description = "Date (yyyy-MM-dd)", example = "2026-10-20")
        private String date;
        @Schema(description = "Expected reads for the day", example = "2")
        private int expectedUses;
        @Schema(description = "Actual reads for the day", example = "2")
        private int actualUses;
        @Schema(description = "Allowed: correct, incomplete", example = "correct")
        private String status;
    }
}

