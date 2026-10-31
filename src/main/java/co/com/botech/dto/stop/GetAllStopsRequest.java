package co.com.botech.dto.stop;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Route stops search")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GetAllStopsRequest {
    @Schema(description = "Route ID", example = "12")
    private Long idRoute;
    @Schema(description = "Whether to include route waypoints", example = "false")
    private boolean includeRouteWaypoints;
}
