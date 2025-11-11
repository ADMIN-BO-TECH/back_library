package co.com.botech.util;

import lombok.experimental.UtilityClass;
import java.util.Arrays;
import java.util.stream.Collectors;

@UtilityClass
public class TextUtils {
    public static String capitalizeEachWord(String str) {
        if (str == null || str.isBlank()) return str;
        return Arrays.stream(str.trim().split("\\s+"))
                .map(w -> w.isEmpty() ? w : Character.toUpperCase(w.charAt(0)) + w.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}