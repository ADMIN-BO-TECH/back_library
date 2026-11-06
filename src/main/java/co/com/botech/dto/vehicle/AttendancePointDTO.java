package co.com.botech.dto.vehicle;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Attendance point")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendancePointDTO {
    @Schema(description = "Hour")
    private String hour;
    @Schema(description = "Latitude")
    private double latitude;
    @Schema(description = "Longitude")
    private double longitude;
    @Schema(description = "Fleet number")
    private String fleetNumber;
}
