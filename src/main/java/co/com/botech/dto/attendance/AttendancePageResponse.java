package co.com.botech.dto.attendance;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendancePageResponse implements AttendanceResponseProjection {
    private Long attendanceRegisterId;
    private LocalDateTime attendanceDateTime;
    private String schoolIdentifier;
    private String typeUser;
    private String userName;
    private String category;
    private String systemName;
    private String registerType;
    private String rfidTag;
    private String rfidDescription;
}
