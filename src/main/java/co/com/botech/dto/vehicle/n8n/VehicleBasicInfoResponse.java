package co.com.botech.dto.vehicle.n8n;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicle basic info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleBasicInfoResponse {
    @Schema(description = "Label")
    private String label;
    @Schema(description = "Plate number")
    private String plateNumber;
}
