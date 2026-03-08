package co.com.botech.dto.stop;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GetAllStopsRequest {
    private Long idRoute;
    private boolean includeRouteWaypoints;
}
