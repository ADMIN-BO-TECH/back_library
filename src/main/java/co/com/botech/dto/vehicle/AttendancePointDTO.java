package co.com.botech.dto.vehicle;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendancePointDTO {
    private String hour;
    private double latitude;
    private double longitude;
    private String fleetNumber;
}
