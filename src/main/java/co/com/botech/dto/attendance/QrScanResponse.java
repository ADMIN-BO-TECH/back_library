package co.com.botech.dto.attendance;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QrScanResponse {
    private boolean success;
    private String studentRecordId;
    private String eventId;
    private String newStatus;
}
