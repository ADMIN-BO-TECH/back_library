package co.com.botech.dto.parent;

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
public class ParentExcelObject {

    @JsonProperty("Id del Acudiente")
    @Min(value = 1, message = "El id del acudiente debe ser un número positivo")
    private Long parentId;

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

    @JsonProperty("Nombres")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "Los nombres contienen caracteres inválidos"
    )
    private String firstName;

    @JsonProperty("Apellidos")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "Los apellidos contienen caracteres inválidos"
    )
    private String lastName;

    @JsonProperty("Teléfono")
    @NotNull
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "El número de teléfono del acudiente debe tener exactamente 10 dígitos"
    )
    private String phoneNumber;

    @JsonProperty("Email")
    @NotNull
    @Email(message = "El email contiene caracteres inválidos")
    private String email;

    @JsonProperty("Tipo de Relación")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "La relación contiene caracteres inválidos"
    )
    private String relationshipType;

    @JsonProperty("Código de Familia")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El codigo de familia contiene caracteres inválidos"
    )
    private String familyCode;

}
