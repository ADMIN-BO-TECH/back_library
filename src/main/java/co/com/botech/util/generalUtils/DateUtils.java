package co.com.botech.util.generalUtils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtils {
    public static LocalDate prepareDateFormat(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateTimeString, formatter);
    }

    public static boolean validateInitEndDateRange(LocalDate initDate, LocalDate endDate) {
        return !endDate.isBefore(initDate) && !initDate.isBefore(LocalDate.now());
    }

    public static boolean currentDateInRange(LocalDate initDate, LocalDate endDate , LocalDate now){
        return !initDate.isAfter(now) && !endDate.isBefore(now);
    }
}

