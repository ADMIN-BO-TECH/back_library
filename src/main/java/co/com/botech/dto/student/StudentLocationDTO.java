package co.com.botech.dto.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student location")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentLocationDTO {
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
    @Schema(description = "Home address")
    private String homeAddress;
    @Schema(description = "Home latitude")
    private Double homeLatitude;
    @Schema(description = "Home longitude")
    private Double homeLongitude;
}