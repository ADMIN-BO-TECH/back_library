package co.com.botech.dto.geofence;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusLocationEvent {

    private String rfidTag;
    private Double latitude;
    private Double longitude;
}
