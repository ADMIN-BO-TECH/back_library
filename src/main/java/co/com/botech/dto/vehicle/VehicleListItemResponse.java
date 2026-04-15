package co.com.botech.dto.vehicle;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleListItemResponse {
    private Long idVehicle;
    private String fleetNumber;
    private String plateNumber;
    private String RfidTag;
}
