package co.com.botech.dto.parent;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Family parent")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentDTO {
    @Schema(description = "Parent ID", example = "77")
    @Min(value = 1, message = "El id del acudiente debe ser un número positivo")
    private Long parentId;
    @Schema(description = "Document type ID", example = "1")
    @NotNull
    @Min(value = 1, message = "El id del tipo de documento debe ser un número positivo")
    private Long documentTypeId;
    @Schema(description = "Parent's document number", example = "52123456")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El numero de documento contiene caracteres inválidos")
    private String documentNumber;
    @Schema(description = "Parent's first names", example = "María")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "Los nombres contienen caracteres inválidos")
    private String firstName;
    @Schema(description = "Parent's last names", example = "Pérez")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "Los apellidos contienen caracteres inválidos")
    private String lastName;
    @Schema(description = "Mobile number (10 digits)", example = "3001234567")
    @NotNull
    @Pattern(regexp = "^[0-9]{10}$", message = "El número de teléfono del acudiente debe tener exactamente 10 dígitos")
    private String phoneNumber;
    @Schema(description = "Email", example = "maria.perez@correo.com")
    @NotNull
    @Email(message = "El email contiene caracteres inválidos")
    private String email;
    @Schema(description = "Relationship to the student", example = "Madre")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La relación contiene caracteres inválidos")
    private String relationshipType;
    @Schema(description = "Family code", example = "12345678")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El codigo de familia contiene caracteres inválidos")
    private String familyCode;
    @Schema(description = "School name", example = "Colegio San José")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El colegio contiene caracteres inválidos")
    private String schoolName;
}
