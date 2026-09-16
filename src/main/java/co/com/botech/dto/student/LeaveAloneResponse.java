package co.com.botech.dto.student;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveAloneResponse {
    private String referenceDate;
    private String timezone;
    private int total;
    private List<LeaveAloneStudentDTO> items;
}
