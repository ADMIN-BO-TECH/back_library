package co.com.botech.constants;

import lombok.Getter;

@Getter
public enum StudentJourneyStatus {
    PENDING("pending"),
    BOARDED("boarded"),
    DESCENDED("descended"),
    NOT_COMING("not_coming");

    private final String wireValue;

    StudentJourneyStatus(String wireValue) {
        this.wireValue = wireValue;
    }
}
