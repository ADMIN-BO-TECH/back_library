package co.com.botech.dto.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentLocationDTO {
    private Long studentRecordId;
    private Long studentId;
    private String firstName;
    private String gradeLevel;
    private String homeAddress;
    private Double homeLatitude;
    private Double homeLongitude;
}