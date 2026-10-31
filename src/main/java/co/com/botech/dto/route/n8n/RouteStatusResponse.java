package co.com.botech.dto.route.n8n;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Route status")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class RouteStatusResponse {
    @Schema(description = "Student school ID")
    private Long studentSchoolId;
    @Schema(description = "Student name")
    private String studentName;
    @Schema(description = "Has active route")
    private Boolean hasActiveRoute;
    @Schema(description = "Reason")
    private String reason;
    @Schema(description = "Reason label")
    private String reasonLabel;
    @Schema(description = "Active route")
    private RouteSummary activeRoute;
    @Schema(description = "Next route")
    private NextRouteInfo nextRoute;

    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
    public static class NextRouteInfo {
        @Schema(description = "Route ID")
        private Long routeId;
        @Schema(description = "Name")
        private String name;
        @Schema(description = "Scheduled time")
        private String scheduledTime;
    }
}
