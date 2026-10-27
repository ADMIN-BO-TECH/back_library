package co.com.botech.dto.route.tracking;

import lombok.*;

@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class RouteLinkData {
    private Long id;
    private String name;
    private String routeStartTime;
    private String routeEndTime;
    private String fleetNumber;
    private String vehicleRfidTag;
    private String totalEncryptedPolyline;
}
