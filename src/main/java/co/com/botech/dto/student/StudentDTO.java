package co.com.botech.dto.student;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {
    @Min(value = 1, message = "El ID de Registro del estudiante debe ser un número positivo")
    private Long studentRecordId;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El nombre no puede contener caracteres especiales")
    private String firstName;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El curso no puede contener caracteres especiales")
    private String gradeLevel;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La dirección no puede contener caracteres especiales")
    private String homeAddress;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El apellido no puede contener caracteres especiales")
    private String lastName;
    @NotNull
    private boolean leaveAlone;
    @NotNull
    private boolean pickUpAlone;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El tag RfID no puede contener caracteres especiales")
    private String rfidTag;
    @NotNull
    @Min(value = 1, message = "El ID de Colegio del estudiante debe ser un número positivo")
    private Long studentId;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El transporte no puede contener caracteres especiales")
    private String transport;
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El codigo de Familia no puede contener caracteres especiales")
    private String familyCode;
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El colegio no puede contener caracteres especiales")
    private String schoolName;
}
