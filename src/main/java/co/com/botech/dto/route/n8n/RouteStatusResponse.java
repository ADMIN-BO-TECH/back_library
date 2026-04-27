package co.com.botech.dto.route.n8n;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class RouteStatusResponse {
    private Long studentSchoolId;
    private String studentName;
    private Boolean hasActiveRoute;
    private String reason;
    private String reasonLabel;
    private RouteSummary activeRoute;
    private NextRouteInfo nextRoute;

    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
    public static class NextRouteInfo {
        private Long routeId;
        private String name;
        private String scheduledTime;
    }
}
