package co.com.botech.constants;

import lombok.Getter;

@Getter
public enum AnnouncementStatus {

    DRAFT("Borrador"),
    PUBLISHED("Publicado"),
    ARCHIVED("Archivado");

    private final String description;

    AnnouncementStatus(String description) {
        this.description = description;
    }

    public static AnnouncementStatus fromValue(String value) {
        if (value == null) return null;

        for (AnnouncementStatus status : AnnouncementStatus.values()) {
            if (status.name().equalsIgnoreCase(value)
                    || status.getDescription().equalsIgnoreCase(value)) {
                return status;
            }
        }

        throw new IllegalArgumentException("Invalid AnnouncementStatus: " + value);
    }
}