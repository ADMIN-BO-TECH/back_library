package co.com.botech.dto.student;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Leave alone student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveAloneStudentDTO {
    @Schema(description = "Student record ID")
    private Long studentRecordId;
    @Schema(description = "Student ID")
    private Long studentId;
    @Schema(description = "First name")
    private String firstName;
    @Schema(description = "Last name")
    private String lastName;
    @Schema(description = "Grade level")
    private String gradeLevel;
    @Schema(description = "Family code")
    private String familyCode;
    @Schema(description = "Home address")
    private String homeAddress;
    @Schema(description = "Transport")
    private String transport;
    @Schema(description = "Last card read")
    private LastCardReadDTO lastCardRead;
}
