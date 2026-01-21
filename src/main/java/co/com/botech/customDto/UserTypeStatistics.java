package co.com.botech.customDto;

public interface UserTypeStatistics {

    String getUserType();

    Long getTotalUsers();

    Long getEntryRegisters();

    Long getExitRegisters();

    Long getTotalRegisters();

    Long getDistinctUsersWithRegisters();

    Double getUserUsePercentage();

    Double getUserAverageRegisters();
}
