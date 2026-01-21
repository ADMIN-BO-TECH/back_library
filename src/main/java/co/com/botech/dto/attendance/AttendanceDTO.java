package co.com.botech.dto.attendance;

import co.com.botech.dto.rfidRegister.RfidRegisterDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceDTO {
    private Long attendanceRegisterId;
    private LocalDateTime attendanceDateTime;
    private String systemName;
    private String registerType;
    private RfidRegisterDTO rfidRegister;
}
