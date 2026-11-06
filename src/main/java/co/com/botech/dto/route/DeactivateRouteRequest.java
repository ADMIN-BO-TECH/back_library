package co.com.botech.dto.route;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Deactivate routes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeactivateRouteRequest {
    @Schema(description = "Route IDs", example = "[12, 13]")
    private List<Long> routeIds;
}