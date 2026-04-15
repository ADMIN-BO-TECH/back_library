package co.com.botech.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentExcelObject {

    @JsonProperty("Id de Registro del Estudiante")
    @Min(value = 1, message = "El ID de Registro del estudiante debe ser un número positivo")
    private Long studentRecordId;

    @JsonProperty("Nombre")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El nombre no puede contener caracteres especiales")
    private String firstName;

    @JsonProperty("Apellido")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El apellido no puede contener caracteres especiales")
    private String lastName;

    @JsonProperty("Curso")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El curso no puede contener caracteres especiales")
    private String gradeLevel;

    @JsonProperty("Dirección")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "La dirección no puede contener caracteres especiales")
    private String homeAddress;

    @JsonProperty("Salida Solo")
    private Boolean leaveAlone;

    @JsonProperty("Recogida Solo")
    private Boolean pickUpAlone;

    @JsonProperty("Tag RfID")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El tag RfID no puede contener caracteres especiales")
    private String rfidTag;

    @JsonProperty("Id de Colegio del Estudiante")
    @NotNull
    @Min(value = 1, message = "El ID de Colegio del estudiante debe ser un número positivo")
    private Long studentId;

    @JsonProperty("Transporte")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El transporte no puede contener caracteres especiales")
    private String transport;

    @JsonProperty("Código de Familia")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El codigo de Familia no puede contener caracteres especiales")
    private String familyCode;
}