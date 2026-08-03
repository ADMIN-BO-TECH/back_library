package co.com.botech.dto.attendance;

import lombok.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QrScanRequest {
    private String studentQrPayload;
    private Long busId;
    private Long driverId;
    private OffsetDateTime timestamp;
    private Double lat;
    private Double lng;
    private String type;
}
