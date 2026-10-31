package co.com.botech.dto.route.tracking;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Public route tracking link data")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RouteLinkResponse {
    @Schema(description = "Route data")
    private RouteLinkData route;
    @Schema(description = "Route stops")
    private List<StopDtoTracking> stops;
    @Schema(description = "Whether the link shows bus location", example = "true")
    private Boolean showUbication;
}