package co.com.botech.dto.vehicle.n8n;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleBasicInfoResponse {
    private String label;
    private String plateNumber;
}
