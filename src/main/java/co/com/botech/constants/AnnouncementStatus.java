package co.com.botech.constants;

public enum AnnouncementStatus {

    DRAFT("DRAFT"),
    PUBLISHED("PUBLISHED"),
    ARCHIVED("ARCHIVED");

    private final String name;

    AnnouncementStatus(String name) {
        this.name = name;
    }

}