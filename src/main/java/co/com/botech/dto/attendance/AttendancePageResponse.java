package co.com.botech.dto.attendance;

import lombok.*;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Attendance page")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendancePageResponse implements AttendanceResponseProjection {
    @Schema(description = "Attendance register ID")
    private Long attendanceRegisterId;
    @Schema(description = "Attendance date time")
    private LocalDateTime attendanceDateTime;
    @Schema(description = "School identifier")
    private String schoolIdentifier;
    @Schema(description = "Type user")
    private String typeUser;
    @Schema(description = "User name")
    private String userName;
    @Schema(description = "Category")
    private String category;
    @Schema(description = "System name")
    private String systemName;
    @Schema(description = "Register type")
    private String registerType;
    @Schema(description = "RFID tag")
    private String rfidTag;
    @Schema(description = "RFID description")
    private String rfidDescription;
}
