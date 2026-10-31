package co.com.botech.dto.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Transport employee (driver or assistant)")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    @Schema(description = "Employee ID", example = "42")
    @Min(value = 1, message = "El id del empleado debe ser un número positivo")
    private Long employeeId;

    @Schema(description = "Document type ID", example = "1")
    @NotNull
    @Min(value = 1, message = "El id del tipo de documento debe ser un número positivo")
    private Long documentTypeId;

    @Schema(description = "Document number", example = "1020304050")
    @NotNull(message = "El número de documento es obligatorio")
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El número de documento contiene caracteres inválidos"
    )
    private String documentNumber;

    @Schema(description = "First names", example = "Juan")
    @NotNull(message = "El nombre es obligatorio")
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El nombre contiene caracteres inválidos"
    )
    private String firstName;

    @Schema(description = "Email", example = "maria.perez@correo.com")
    @NotNull(message = "El correo es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    private String email;

    @Schema(description = "Transport employee position", example = "Conductor")
    @NotNull(message = "El cargo es obligatorio")
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El cargo contiene caracteres inválidos"
    )
    private String position;

    @Schema(description = "Employee RFID card tag", example = "E2003412B802011")
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El RFID contiene caracteres inválidos"
    )
    private String rfidTag;
}
