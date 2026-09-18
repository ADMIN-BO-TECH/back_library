package co.com.botech.customDto;

import java.time.LocalDate;

public interface PreoperationTrendPointProjection {
    LocalDate getBucketDate();
    Long getTotalPreops();
    Long getPreopsWithIssues();
    Long getPreopsWithCritical();
}
