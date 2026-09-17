package co.com.botech.customDto;

import java.time.LocalDate;

public interface PreoperationVehicleRankingProjection {
    Long getVehicleId();
    String getPlateNumber();
    String getFleetNumber();
    String getSchoolName();
    Long getTotalPreops();
    Long getPreopsWithIssues();
    Long getCriticalCount();
    LocalDate getLastPreopDate();
}
