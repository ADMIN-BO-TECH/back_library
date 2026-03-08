package co.com.botech.dto.route;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteStateRequest {
    private Long routeId;
    private boolean overlapConflicts;
}