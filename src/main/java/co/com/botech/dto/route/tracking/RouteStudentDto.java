package co.com.botech.dto.route.tracking;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteStudentDto {
    private Long studentRecordId;
    private Long studentId;
    private String name;
    private String lastName;
    private String familyCode;
    private String status;
    private StopDtoTracking stop;
}