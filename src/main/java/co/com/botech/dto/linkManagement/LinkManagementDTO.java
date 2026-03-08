package co.com.botech.dto.linkManagement;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkManagementDTO {
    private Long linkId;
    private boolean status;
    private String linkUrl;
    private String updatedAt;
    private boolean visibleInApp;
    private String description;
    private String accessKey;
    private Long routeId;
}
