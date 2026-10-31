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
@Schema(description = "Permit request for students")
public class CreatePermitRequest {

    @NotBlank
    @NotNull
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Pattern(
            regexp = "^[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]+$",
            message = "La descripción contiene caracteres de control no permitidos"
    )
    @Schema(description = "Permit reason", example = "Cita médica en la tarde")
    private String description;

    @Schema(description = "Permit date (yyyy-MM-dd); same-day requests only before 10:00 AM", example = "2026-11-03")
    @NotNull
    private LocalDate permitDate;

    @Schema(description = "Student school IDs (student_id), not student_record_id", example = "[20231045, 20231046]")
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
    @Schema(description = "Requester name", example = "María Pérez")
    private String requestedBy;

    @Schema(description = "Allowed: Salida durante horario escolar, Inasistencia, Informativa, Cambio de Recorrido", example = "Salida durante horario escolar")
    @NotBlank
    @NotNull
    private String permitType;
}