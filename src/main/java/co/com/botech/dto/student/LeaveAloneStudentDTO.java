package co.com.botech.dto.student;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveAloneStudentDTO {
    private Long studentRecordId;
    private Long studentId;
    private String firstName;
    private String lastName;
    private String gradeLevel;
    private String familyCode;
    private String homeAddress;
    private String transport;
    private LastCardReadDTO lastCardRead;
}
