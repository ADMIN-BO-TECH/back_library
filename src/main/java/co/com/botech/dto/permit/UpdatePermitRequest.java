package co.com.botech.dto.permit;

import com.google.firebase.database.annotations.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "School response to a permit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePermitRequest {

    @Schema(description = "Responder name", example = "Coordinación de transporte")
    @Pattern(
            regexp = "^[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]+$",
            message = "El responsable contiene caracteres de control no permitidos"
    )
    private String repliedBy;

    @Schema(description = "School response", example = "Permiso aprobado")
    @Pattern(
            regexp = "^[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]+$",
            message = "La respuesta contiene caracteres de control no permitidos"
    )
    private String response;

    @Schema(description = "Allowed: Autorizado, Rechazado, Pendiente, Cancelado. Autorizado and Rechazado notify the family", example = "Autorizado")
    @Pattern(
            regexp = "^[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]+$",
            message = "El estado de permiso contiene caracteres de control no permitidos"
    )
    private String permitStatus;
}