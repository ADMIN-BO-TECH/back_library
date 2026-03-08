package co.com.botech.customDto;

import java.time.LocalDateTime;

public interface TopStudentStatistics {
    Long getStudentId();
    String getFullName();
    String getGrade();
    Long getEntryRegisters();
    Long getExitRegisters();
    Long getTotalRegisters();
    LocalDateTime getLastAttendanceTime();
}
