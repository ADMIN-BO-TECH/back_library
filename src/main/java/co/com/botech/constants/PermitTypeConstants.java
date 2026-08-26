package co.com.botech.constants;

public enum PermitTypeConstants {

    EARLY_EXIT("Salida durante horario escolar"),
    ABSENCE("Inasistencia"),
    INFORMATIVE("Informativa"),
    ROUTE_CHANGE("Cambio de Recorrido"),
    PSYCHOLOGY("Psicología"),
    INFIRMARY("Enfermería");

    private final String description;

    PermitTypeConstants(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static boolean isValidType(String type) {
        if (type == null) return false;
        for (PermitTypeConstants permitType : PermitTypeConstants.values()) {
            if (permitType.getDescription().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }
}