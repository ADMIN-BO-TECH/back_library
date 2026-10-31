package co.com.botech.dto.authorization;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "New authorization to pick up a student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAuthorizationRequest {
    @Schema(description = "Student school ID (student_id), not student_record_id", example = "20231045")
    @NotNull
    @Min(value = 1, message = "El id del estudiante debe ser un número positivo")
    private Long studentSchoolId;
    @Schema(description = "Authorized person's document number", example = "52123456")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El numero de documento de la persona autorizada contiene caracteres inválidos")
    private String authorizedPersonDocument;
    @Schema(description = "Document type ID", example = "1")
    @NotNull
    @Min(value = 1, message = "El id del tipo de documento debe ser un número positivo")
    private Long documentTypeId;
    @Schema(description = "Authorized person's first names", example = "Carlos")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "Los nombres contienen caracteres inválidos")
    private String firstNameAuthorizedPerson;
    @Schema(description = "Authorized person's last names", example = "Rodríguez")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "Los apellidos contienen caracteres inválidos")
    private String lastNameAuthorizedPerson;
    @Schema(description = "Name of person granting authorization", example = "María Pérez")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La persona que autoriza contienen caracteres inválidos")
    private String authorizedBy;
    @Schema(description = "Start date (yyyy-MM-dd); cannot be past", example = "2026-11-03")
    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de inicio debe tener el formato yyyy-MM-dd")
    private String authorizationStart;
    @Schema(description = "End date (yyyy-MM-dd); cannot be before start", example = "2026-11-30")
    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha fin debe tener el formato yyyy-MM-dd")
    private String authorizationEnd;
}
