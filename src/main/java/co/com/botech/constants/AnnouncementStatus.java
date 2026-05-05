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

    public static boolean isValidStatus(String status) {
        if (status == null) return false;

        for (AnnouncementStatus announcementStatus : AnnouncementStatus.values()) {
            if (announcementStatus.name().equalsIgnoreCase(status)
                    || announcementStatus.getDescription().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }
}