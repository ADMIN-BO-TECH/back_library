package co.com.botech.constants;

public enum NotificationCategoryConstants {

    PASS("Comunicados del colegio"),
    SEGUIMIENTO("Comunicados de coordinadores"),
    NOTIFICACIONES_PROGRAMADAS_RECORRIDO("Inicio de recorrido"),
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
