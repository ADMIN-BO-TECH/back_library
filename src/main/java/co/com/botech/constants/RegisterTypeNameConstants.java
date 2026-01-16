package co.com.botech.constants;

public enum RegisterTypeNameConstants {
    QR_REGISTER("QR"),
    IDENTIFICATIION_REGISTER("IDENTIFICACION");
    private final String name;

    RegisterTypeNameConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    }
