package co.com.botech.util.generalUtils;

import com.google.cloud.Timestamp;
import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
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

    public static LocalDateTime setDateTimeFilter(String evaluatedDate, String evaluatedTime, String defaultTimeValue) {
        LocalDateTime dateTime;
        boolean haveDate = evaluatedDate != null;
        if (haveDate) {
            Time timeReceived = evaluatedTime != null
                    ? Time.valueOf(evaluatedTime)
                    : Time.valueOf(defaultTimeValue);
            dateTime = DateUtils.prepareDateFormat(evaluatedDate).atTime(timeReceived.toLocalTime());
        } else {
            dateTime = null;
        }
        return dateTime;
    }

    public static Timestamp setDateTimeFromDayAndHourForFirebase(String date, String hour) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String dateTimeString = date + " " + hour;
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
            Instant instant = dateTime.atZone(ZoneId.of("America/Bogota")).toInstant();
            return Timestamp.of(Date.from(instant));
        } catch (Exception e) {
            log.error("Error al procesar las fechas.", e);
            throw new IllegalArgumentException("Error al procesar las fechas "+date+" "+hour);
        }
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


