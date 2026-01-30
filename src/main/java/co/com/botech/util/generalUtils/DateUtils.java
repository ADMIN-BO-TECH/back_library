package co.com.botech.util.generalUtils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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

    public static List<LocalDate> completeWeek(LocalDate dateInWeek) {
        LocalDate sunday = initWeekSunday(dateInWeek);
        List<LocalDate> days = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            days.add(sunday.plusDays(i));
        }
        return days;
    }

    private static LocalDate initWeekSunday(LocalDate date) {
        int delta = date.getDayOfWeek().getValue() % 7;
        return date.minusDays(delta);
    }
}

