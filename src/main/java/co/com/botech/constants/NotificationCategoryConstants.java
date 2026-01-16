package co.com.botech.constants;

public enum NotificationCategoryConstants {

    PASS("PASS TEST"),
    SEGUIMIENTO("SEGUMIENTO TEST"),
    NOTIFICACIONES_PROGRAMADAS_RECORRIDO("RECORRIDO PROGRAMADAS"),
    ASISTENCIA("ASISTENCIA");

    private final String name;

    NotificationCategoryConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
