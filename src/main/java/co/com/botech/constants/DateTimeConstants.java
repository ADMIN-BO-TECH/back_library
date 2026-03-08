package co.com.botech.constants;

import lombok.Getter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
public class DateTimeConstants {
    public static final String BOGOTA_ZONE = "America/Bogota";
    public static final ZoneId ZONE_ID_BOGOTA = ZoneId.of(BOGOTA_ZONE);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);

}
