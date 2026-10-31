package co.com.botech.dto.schoolEmployee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "School employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolEmployeeDTO {
    @Schema(description = "Employee ID", example = "42")
    @Min(value = 1, message = "El id del empleado debe ser un número positivo")
    private Long employeeId;
    @Schema(description = "Document type ID", example = "1")
    @NotNull
    @Min(value = 1, message = "El id del tipo de documento debe ser un número positivo")
    private Long documentTypeId;
    @Schema(description = "Document number", example = "1020304050")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El numero de documento contiene caracteres inválidos")
    private String documentNumber;
    @Schema(description = "First names", example = "Juan")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "Los nombres contienen caracteres inválidos")
    private String firstName;
    @Schema(description = "Last names", example = "Pérez")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "Los apellidos contienen caracteres inválidos")
    private String lastName;
    @Schema(description = "Generated as \"4\" + document number; sent value is ignored on create", example = "41020304050")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El codigo de familia contiene caracteres inválidos")
    private String familyCode;
    @Schema(description = "Email", example = "maria.perez@correo.com")
    @NotNull
    @Email(message = "El email contiene caracteres inválidos")
    private String email;
    @Schema(description = "School position", example = "Coordinador")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El cargo contiene caracteres inválidos")
    private String position;
    @Schema(description = "School name", example = "Colegio San José")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El colegio contiene caracteres inválidos")
    private String schoolName;
    @Schema(description = "Whether to send a welcome email", example = "true")
    private Boolean sendEmail;
}
