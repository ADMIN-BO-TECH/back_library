package co.com.botech.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceByFamilyCodeResponse {

    private String fullName;

    private String dateTime;

    private String readerName;

    private String attendanceType;
}
