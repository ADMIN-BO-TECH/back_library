package co.com.botech.dto.route.tracking;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteStudentDto {
    private Long studentRecordId;
    private Long studentId;
    private Long schoolEmployeeId;
    private String name;
    private String lastName;
    private String familyCode;
    private StopDtoTracking stop;
}