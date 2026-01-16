package co.com.botech.util.generalUtils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static co.com.botech.constants.DateTimeConstants.*;

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

    public static LocalDateTime getDateTimeByTime(String hour) {
        LocalTime time = convertHourByFormat(hour);
        return LocalDate.now(ZONE_ID_BOGOTA).atTime(time);
    }

    public static LocalTime convertHourByFormat(String hour) {
        try {
            return LocalTime.parse(hour, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de hora no válido: " + hour, e);
        }
    }
}

