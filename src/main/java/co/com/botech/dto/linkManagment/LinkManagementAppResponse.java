package co.com.botech.dto.linkManagment;
import lombok.*;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Route tracking link")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkManagementAppResponse {
    @Schema(description = "Link ID", example = "5")
    private Long id;
    @Schema(description = "Public route tracking URL", example = "https://botech.com.co/seguimiento/abc123")
    private String linkUrl;
    @Schema(description = "Link description", example = "Seguimiento ruta 12 mañana")
    private String description;
    @Schema(description = "Last updated date and time", example = "2026-10-21T09:15:00")
    private LocalDateTime updatedAt;
    @Schema(description = "Route ID", example = "12")
    private String routeId;
    @Schema(description = "Route name", example = "Ruta 12 - Mañana")
    private String routeName;
}
