package co.com.botech.dto.linkManagement;

import jakarta.validation.constraints.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "New route tracking link")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLinkManagementRequest {

    @Schema(description = "Whether the link shows in the family app", example = "true")
    @NotNull(message = "El campo visibleInApp es obligatorio")
    private Boolean visibleInApp;

    @Schema(description = "Link description", example = "Seguimiento ruta 12 mañana")
    private String description;

    @Schema(description = "Route ID", example = "12")
    @NotNull(message = "El routeId es obligatorio")
    private Long routeId;
}