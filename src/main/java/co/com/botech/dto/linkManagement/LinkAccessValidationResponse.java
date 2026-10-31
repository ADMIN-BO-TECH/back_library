package co.com.botech.dto.linkManagement;

import co.com.botech.dto.stop.StopDTO;
import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Link access validation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkAccessValidationResponse {

    @Schema(description = "Shows the location")
    private Boolean showUbication;
    @Schema(description = "Vehicle RFID tag")
    private String vehicleRfidTag;
    @Schema(description = "Stops")
    private List<StopDTO> stops;
    @Schema(description = "Encrypted route polyline")
    private String totalEncryptedPolyline;
}