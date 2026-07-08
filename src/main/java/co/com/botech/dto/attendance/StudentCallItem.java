package co.com.botech.dto.attendance;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentCallItem {
    private String studentId;
    private String name;
    private String status;
    private String grade;
}
