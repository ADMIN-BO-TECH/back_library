package co.com.botech.constants;

public enum SystemNameConstants {
    PASS_SYSTEM("PASS"),
    SEGUIMIENTO_SYSTEM("Bajada Bus");
    private final String name;

    SystemNameConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    }
