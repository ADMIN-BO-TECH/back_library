package co.com.botech.dto.notification;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Enable or disable a notification category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCategoryPreferenceDTO {
    @Schema(description = "Allowed: Comunicados del colegio, Comunicados de coordinadores, Inicio de recorrido, Asistencia, Geocercas", example = "Asistencia")
    private String categoryName;
    @Schema(description = "Whether the category is enabled", example = "true")
    private Boolean enabled;
}
