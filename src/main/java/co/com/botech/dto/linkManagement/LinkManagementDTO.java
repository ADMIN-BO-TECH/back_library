package co.com.botech.dto.linkManagement;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkManagementDTO {
    private Long linkId;
    private Long routeId;
    private String RouteName;
    private String linkUrl;
    private Boolean status;
    private String updatedAt;
    private Boolean visibleInApp;
    private String description;
}