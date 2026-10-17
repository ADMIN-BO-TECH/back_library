package co.com.botech.dto.permit;

import com.google.firebase.database.annotations.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Solicitud de permiso para uno o varios estudiantes")
public class CreatePermitRequest {

    @NotBlank
    @NotNull
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Pattern(
            regexp = "^[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]+$",
            message = "La descripción contiene caracteres de control no permitidos"
    )
    @Schema(description = "Motivo del permiso", example = "Cita médica en la tarde")
    private String description;

    @Schema(description = "Fecha del permiso", example = "2026-10-20")
    @NotNull
    private LocalDate permitDate;

    @Schema(description = "IDs de los estudiantes (student_record_id)", example = "[1024, 1025]")
    @NotNull
    @NotEmpty
    private List<Long> studentIds;

    @NotBlank
    @NotNull
    @Size(max = 200, message = "El campo 'solicitado por' no puede superar los 200 caracteres")
    @Pattern(
            regexp = "^[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]+$",
            message = "El campo 'solicitado por' contiene caracteres de control no permitidos"
    )
    @Schema(description = "Nombre de quien solicita el permiso", example = "María Pérez")
    private String requestedBy;

    @Schema(description = "Tipo de permiso", example = "SALIDA_ANTICIPADA")
    @NotBlank
    @NotNull
    private String permitType;
}