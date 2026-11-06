package co.com.botech.dto.linkManagement;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Link management")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkManagementDTO {
    @Schema(description = "Link ID")
    private Long linkId;
    @Schema(description = "Route ID")
    private Long routeId;
    @Schema(description = "Route name")
    private String RouteName;
    @Schema(description = "Link URL")
    private String linkUrl;
    @Schema(description = "Status")
    private Boolean status;
    @Schema(description = "Updated at")
    private String updatedAt;
    @Schema(description = "Visible in app")
    private Boolean visibleInApp;
    @Schema(description = "Description")
    private String description;
}