package co.com.botech.constants;

public enum PermitTypeConstants {

    SICKNESS("Enfermedad"),
    ROUTE_CHANGE("Cambio de Recorrido"),
    ABSENCE("Inasistencia");
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
            if (permitType.name().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }
}
