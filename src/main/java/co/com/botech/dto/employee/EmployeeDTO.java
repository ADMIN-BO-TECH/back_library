package co.com.botech.dto.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    @Min(value = 1, message = "El id del empleado debe ser un número positivo")
    private Long employeeId;

    @NotNull(message = "El tipo de documento es obligatorio")
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El tipo de documento contiene caracteres inválidos"
    )
    private String documentType;

    @NotNull(message = "El número de documento es obligatorio")
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El número de documento contiene caracteres inválidos"
    )
    private String documentNumber;

    @NotNull(message = "El nombre es obligatorio")
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El nombre contiene caracteres inválidos"
    )
    private String firstName;

    @NotNull(message = "El correo es obligatorio")
    @Email(message = "El correo electrónico no es válido")
    private String email;

    @NotNull(message = "El cargo es obligatorio")
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El cargo contiene caracteres inválidos"
    )
    private String position;

    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El RFID contiene caracteres inválidos"
    )
    private String rfidTag;
}
