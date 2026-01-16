package co.com.botech.dto.attendance;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterRequest {

    private String id;
    private String generalFilter;
    private String rfidRegister;
    private String typeAttendance;
    private String startDate;
    private String endDate;
    private String startHour;
    private String endHour;
    private boolean student;
    private boolean employee;
    private boolean parent;
    private boolean authorizedPerson;
}
