package co.com.botech.dto.preoperation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PreoperationSubmitResponse {
    private boolean success;
    private Long id;
    private String vehicleStatus;
    private boolean hasIssues;
    private boolean hasCritical;
    private Integer issueCount;
}
