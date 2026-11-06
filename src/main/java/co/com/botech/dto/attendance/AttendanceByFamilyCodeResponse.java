package co.com.botech.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student or employee attendance record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceByFamilyCodeResponse {

    @Schema(description = "Person's full name", example = "Juan Pérez")
    private String fullName;
    @Schema(description = "Record date and time", example = "2026-10-20 06:45:12")
    private String dateTime;
    @Schema(description = "RFID reader name", example = "Bus 12 - Puerta")
    private String readerName;
    @Schema(description = "Type: entry or exit", example = "entry")
    private String attendanceType;
    @Schema(description = "Latitude", example = "4.7110")
    private Double latitude;
    @Schema(description = "Longitude", example = "-74.0721")
    private Double longitude;
}
