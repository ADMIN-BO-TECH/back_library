package co.com.botech.constants;

public enum NotificationCategoryConstants {

    PASS("Comunicados"),
    SEGUIMIENTO("Comunicados"),
    NOTIFICACIONES_PROGRAMADAS_RECORRIDO("RECORRIDO PROGRAMADAS"),
    ASISTENCIA("Asistencia"),
    GEOCERCAS("Geocercas");

    private final String name;

    NotificationCategoryConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
