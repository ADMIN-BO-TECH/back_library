package co.com.botech.constants;

public enum NotificationChannelConstants {

    PUSH("PUSH"),
    EMAIL("EMAIL"),
    IN_APP("IN_APP");

    private final String name;

    NotificationChannelConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}