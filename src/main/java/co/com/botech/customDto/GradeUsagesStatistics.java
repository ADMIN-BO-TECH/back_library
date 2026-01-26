package co.com.botech.customDto;

public interface GradeUsagesStatistics {
    String getGrade();
    Long getStudentsWithRegisters();
    Long getTotalRegisters();
    Double getUsagePercentage();
}
