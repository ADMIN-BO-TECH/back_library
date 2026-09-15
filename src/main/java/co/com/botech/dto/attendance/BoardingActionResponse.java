package co.com.botech.dto.attendance;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardingActionResponse {
    private boolean success;
    private String studentId;
    private String studentSchoolId;
    private String status;
    private String message;
}