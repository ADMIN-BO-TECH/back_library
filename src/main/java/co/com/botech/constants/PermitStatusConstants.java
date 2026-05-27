package co.com.botech.constants;

public enum PermitStatusConstants {

    AUTHORIZED("Autorizado"),
    REFUSED("Rechazado"),
    PENDING("Pendiente");
    private final String description;

    PermitStatusConstants(String description) {
        this.description = description;
    }

    public static boolean isValidStatus(String status) {
        if (status == null) return false;
        for (PermitStatusConstants permitStatus : PermitStatusConstants.values()) {
            if (permitStatus.getDescription().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }

    public String getDescription() {
        return description;
    }
}
