package co.com.botech.customDto;

import java.time.LocalDate;

public interface DailyAttendanceStatistics {

    LocalDate getDay();

    Long getTotalRegisters();

    Long getStudentRegisters();

    Long getEmployeeRegisters();

    Long getParentRegisters();

    Long getAuthorizedPersonRegisters();
}