package co.com.botech.constants;

public enum StopInformationStateConstants {
    ACTIVE("ACTIVA"),
    DYNAMIC("DINAMICA"),
    INACTIVE("INACTIVA"),
    TO_ACTIVATE("POR ACTIVAR");

    private final String name;

    StopInformationStateConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
