package co.com.botech.utilObjects;

import co.com.botech.entity.Attendance;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceSearchObject {
    private List<Attendance> attendanceList;
    private Integer attendanceCount;
}
