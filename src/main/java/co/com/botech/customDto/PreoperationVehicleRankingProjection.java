package co.com.botech.customDto;

import java.time.LocalDate;

public interface PreoperationVehicleRankingProjection {
    Long getVehicleId();
    String getPlateNumber();
    String getFleetNumber();
    Long getTotalPreops();
    Long getPreopsWithIssues();
    Long getCriticalCount();
    LocalDate getLastPreopDate();
}
