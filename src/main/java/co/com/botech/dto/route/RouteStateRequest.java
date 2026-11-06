package co.com.botech.dto.route;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Route status change or validation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteStateRequest {
    @Schema(description = "Route ID", example = "12")
    private Long routeId;
    @Schema(description = "Whether to allow schedule conflicts", example = "false")
    private boolean overlapConflicts;
}