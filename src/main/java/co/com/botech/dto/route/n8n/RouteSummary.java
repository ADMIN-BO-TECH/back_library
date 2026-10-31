package co.com.botech.dto.route.n8n;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Route summary")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSummary {
    @Schema(description = "Route ID")
    private Long routeId;
    @Schema(description = "Name")
    private String name;
    @Schema(description = "Status")
    private String status;
}
