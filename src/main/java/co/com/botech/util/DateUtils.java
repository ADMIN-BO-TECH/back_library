package co.com.botech.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtils {
    public static LocalDate prepareDateFormat(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateTimeString, formatter);
    }

    private static boolean validateInitEndDateRange(LocalDate inicioPermiso, LocalDate finPermiso) {
        return !finPermiso.isBefore(inicioPermiso) && !inicioPermiso.isBefore(LocalDate.now());
    }
}

