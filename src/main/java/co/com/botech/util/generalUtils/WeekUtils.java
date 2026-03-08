package co.com.botech.util.generalUtils;

import lombok.experimental.UtilityClass;
import java.time.*;
import java.util.*;

@UtilityClass
public class WeekUtils {
    public static final ZoneId ZONE_BOGOTA = ZoneId.of("America/Bogota");

    public static LocalDate startOfWeekSunday(LocalDate date) {
        int delta = date.getDayOfWeek().getValue() % 7; // Dom=0, Lun=1, ...
        return date.minusDays(delta);
    }

    public static List<LocalDate> fullWeek(LocalDate anyDay) {
        LocalDate sunday = startOfWeekSunday(anyDay);
        List<LocalDate> days = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) days.add(sunday.plusDays(i));
        return days;
    }

    public static LocalDateTime startOfDay(LocalDate d) { return d.atStartOfDay(); }
    public static LocalDateTime nextStartOfDay(LocalDate d) { return d.plusDays(1).atStartOfDay(); }

    /** Verifica si una cadena tipo "l,m,x,j,v" incluye el día de 'date' */
    public static boolean includesDay(String diasRecorrido, LocalDate date) {
        if (diasRecorrido == null || diasRecorrido.isBlank()) return false;
        char ch = switch (date.getDayOfWeek()) {
            case MONDAY -> 'l'; case TUESDAY -> 'm'; case WEDNESDAY -> 'x';
            case THURSDAY -> 'j'; case FRIDAY -> 'v'; case SATURDAY -> 's'; case SUNDAY -> 'd';
        };
        String norm = diasRecorrido.toLowerCase(Locale.ROOT).replace(" ", "");
        for (String t : norm.split(",")) if (t.equals(String.valueOf(ch))) return true;
        return false;
    }
}
