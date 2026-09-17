package co.com.botech.customDto;

public interface PreoperationFleetSummaryProjection {
    Long getTotalPreops();
    Long getPreopsWithIssues();
    Long getPreopsWithCritical();
    Long getTotalVehicles();
    Long getVehiclesWithIssues();
    Long getVehiclesWithCritical();
}
