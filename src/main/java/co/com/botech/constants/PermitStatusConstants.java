package co.com.botech.constants;

public enum PermitStatusConstants {

    ACTIVE("Activo"),
    INACTIVE("Inactivo"),
    PENDING("Pendiente");
    private final String description;

    PermitStatusConstants(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

        public static boolean isValidStatus(String status) {
        if (status == null) return false;
        for (PermitStatusConstants permitStatus : PermitStatusConstants.values()) {
            if (permitStatus.name().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }
}
