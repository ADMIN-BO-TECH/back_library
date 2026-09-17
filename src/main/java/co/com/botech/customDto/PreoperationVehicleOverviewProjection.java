package co.com.botech.customDto;

import java.time.LocalDate;

public interface PreoperationVehicleOverviewProjection {
    Long getVehicleId();
    String getPlateNumber();
    String getFleetNumber();
    String getSchoolName();
    Integer getLastKilometrage();
    LocalDate getLastPreopDate();
    String getLastOperator();
    Long getTotalPreops();
    Long getPreopsWithIssues();
    Long getPreopsWithCritical();
}
