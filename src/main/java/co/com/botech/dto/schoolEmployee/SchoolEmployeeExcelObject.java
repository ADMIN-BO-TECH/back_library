package co.com.botech.dto.schoolEmployee;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class SchoolEmployeeExcelObject {

    @JsonProperty("Id del Empleado")
    @Min(value = 1, message = "El id del empleado debe ser un número positivo")
    private Long employeeId;

    @JsonProperty("Tipo de Documento")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El tipo de documento contiene caracteres inválidos"
    )
    private String documentType;

    @JsonProperty("Número de Documento")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El numero de documento contiene caracteres inválidos"
    )
    private String documentNumber;

    @JsonProperty("Nombre")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "Los nombres contienen caracteres inválidos"
    )
    private String firstName;

    @JsonProperty("Apellido")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "Los apellidos contienen caracteres inválidos"
    )
    private String lastName;

    @JsonProperty("Código de Familia")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El codigo de familia contiene caracteres inválidos"
    )
    private String familyCode;

    @JsonProperty("Email")
    @NotNull
    @Email(message = "El email contiene un formato inválido")
    private String email;

    @JsonProperty("Cargo")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El cargo contiene caracteres inválidos"
    )
    private String position;
}