package co.com.botech.dto.linkManagement;

import co.com.botech.dto.stop.StopDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkAccessValidationResponse {

    private Boolean showUbication;
    private String vehicleRfidTag;
    private List<StopDTO> stops;
    private String totalEncryptedPolyline;
}