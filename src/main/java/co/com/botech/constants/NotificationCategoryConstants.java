package co.com.botech.constants;

public enum NotificationCategoryConstants {

    PASS("PASS TEST"),
    SEGUIMIENTO("SEGUMIENTO TEST"),
    ASISTENCIA("ASISTENCIA");

    private final String name;

    NotificationCategoryConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
