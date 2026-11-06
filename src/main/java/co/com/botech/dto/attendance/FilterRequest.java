package co.com.botech.dto.attendance;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Attendance history filters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterRequest {

    @Schema(description = "Attendance record ID. Separate values with \"|\"; partial match", example = "1024")
    private String id;
    @Schema(description = "Name, document, grade or school ID. Separate values with \"|\"; partial match", example = "Pérez")
    private String generalFilter;
    @Schema(description = "RFID reader description, tag or device type. Separate values with \"|\"; partial match", example = "Bus 12 - Puerta")
    private String rfidRegister;
    @Schema(description = "Allowed: Subida Bus, Bajada Bus, Entrada Portería, Salida Portería. Separate values with \"|\"", example = "Subida Bus|Bajada Bus")
    private String typeAttendance;
    @Schema(description = "Start date (yyyy-MM-dd)", example = "2026-10-01")
    private String startDate;
    @Schema(description = "End date (yyyy-MM-dd)", example = "2026-10-31")
    private String endDate;
    @Schema(description = "Start time (HH:mm:ss); default 00:00:00", example = "06:00:00")
    private String startHour;
    @Schema(description = "End time (HH:mm:ss); default 23:59:59", example = "08:00:00")
    private String endHour;
    @Schema(description = "Include students", example = "true")
    private boolean student;
    @Schema(description = "Include employees", example = "false")
    private boolean employee;
    @Schema(description = "Include parents", example = "false")
    private boolean parent;
    @Schema(description = "Include authorized people", example = "false")
    private boolean authorizedPerson;
}
