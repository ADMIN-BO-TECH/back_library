package co.com.botech.constants;

import co.com.botech.util.generalUtils.CustomException;
import lombok.Getter;

@Getter
public enum AnnouncementStatus {

    DRAFT("DRAFT"),
    PUBLISHED("PUBLISHED"),
    ARCHIVED("ARCHIVED");

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

        throw new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND, "Invalid AnnouncementStatus: " + value);
    }
}