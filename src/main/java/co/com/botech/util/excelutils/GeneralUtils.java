package co.com.botech.util.excelutils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralUtils {

    private GeneralUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> getHeaders(Class<?> selectedClass) {
        return Arrays.stream(selectedClass.getDeclaredFields())
                .map(Field::getName)
                .toList();
    }

    public static List<? extends Class<?>> getTypes(Class<?> selectedClass) {
        return Arrays.stream(selectedClass.getDeclaredFields())
                .map(Field::getType)
                .toList();
    }

    public static List<String> getColumnNames(Class<?> selectedClass) {
        return Arrays.stream(selectedClass.getDeclaredFields())
                .map(field -> {
                    JsonProperty name = field.getAnnotation(JsonProperty.class);
                    return name.value();
                })
                .toList();
    }

    public static List<String> setSetMethods(List<String> headers) {
        List<String> getMethods = new ArrayList<>();
        for (String varName : headers) {
            String getMethod = "set" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
            getMethods.add(getMethod);
        }
        return getMethods;
    }

    public static List<String> setGetMethods(List<String> headers) {
        List<String> getMethods = new ArrayList<>();
        for (String varName : headers) {
            String getMethod = "get" + varName.substring(0, 1).toUpperCase() + varName.substring(1);
            getMethods.add(getMethod);
        }
        return getMethods;
    }
}
