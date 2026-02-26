package co.com.botech.dto.authorizedPerson;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorizedPersonExcelObject {
    @JsonProperty("Id Persona Autorizada")
    @Min(value = 1, message = "El id de la persona autorizada debe ser un número positivo")
    private Long authorizedPersonId;

    @JsonProperty("Tipo Documento")
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

    @JsonProperty("Autorizado Por")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "La persona que autoriza contiene caracteres inválidos"
    )
    private String authorizedBy;

    @JsonProperty("Código de Familia")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El codigo de familia contiene caracteres inválidos"
    )
    private String familyCode;

    @JsonProperty("Fecha Creación")
    private LocalDateTime createdAt;

    @JsonProperty("Fecha Actualización")
    private LocalDateTime updatedAt;

    @JsonProperty("Fecha Inicio Autorización")
    @NotNull
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "La fecha de inicio debe tener el formato yyyy-MM-dd"
    )
    private String authorizationStart;

    @JsonProperty("Fecha Fin Autorización")
    @NotNull
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "La fecha fin debe tener el formato yyyy-MM-dd"
    )
    private String authorizationEnd;

    @JsonProperty("Descripción")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "La descripción contiene caracteres inválidos"
    )
    private String description;

    @JsonProperty("Tipo")
    @NotNull
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El tipo contiene caracteres inválidos"
    )
    private String type;

    @JsonProperty("Nombre Colegio")
    private String schoolName;
}
