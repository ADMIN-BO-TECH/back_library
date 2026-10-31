package co.com.botech.dto.linkManagement;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Route tracking link update")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateLinkManagementRequest {
    @Schema(description = "Tracking link ID", example = "5")
    @NotNull(message = "El linkId es obligatorio")
    private Long linkId;
    @Schema(description = "Whether the link shows in the family app", example = "true")
    @NotNull(message = "El campo visibleInApp es obligatorio")
    private Boolean visibleInApp;
    @Schema(description = "Link description", example = "Seguimiento ruta 12 mañana")
    private String description;
}

