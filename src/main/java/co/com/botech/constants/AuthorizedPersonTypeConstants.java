package co.com.botech.constants;

public enum AuthorizedPersonTypeConstants {

    PROVIDERS("Proveedor"),
    FAMILY_MEMBERS("Familiar"),
    GUESTS("Invitado");
    private final String description;

    AuthorizedPersonTypeConstants(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static boolean isValidType(String type) {
        if (type == null) return false;
        for (AuthorizedPersonTypeConstants authorizedPersonType : AuthorizedPersonTypeConstants.values()) {
            if (authorizedPersonType.name().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }
}
