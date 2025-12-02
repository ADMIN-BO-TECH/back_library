package co.com.botech.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    public static LocalDateTime dateTimePreparation(String date, boolean inicio) {
        LocalDateTime dateTime;
        dateTime = tryParseISO(date);
        if (dateTime != null) {
            return dateTime;
        }
        dateTime = tryParseWithPattern(date, inicio);
        return dateTime;
    }

    private static LocalDateTime tryParseISO(String date) {
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
        } catch (Exception e) {
            return null;
        }
    }


    private static LocalDateTime tryParseWithPattern(String date, boolean inicio) {
        DateTimeFormatter[] formatters = new DateTimeFormatter[]{
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                if (formatter == formatters[2]) {
                    LocalDate localDate = LocalDate.parse(date, formatter);
                    return inicio ? localDate.atStartOfDay() : localDate.atTime(23, 59, 59);
                } else {
                    return LocalDateTime.parse(date, formatter);
                }
            } catch (Exception ignored) {
                continue;
            }
        }
        throw new IllegalArgumentException("Formato de fecha inválido: " + date);
    }
}


