package co.com.botech.dto.student;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {
    @Schema(description = "Student record ID (student_record_id)", example = "1024")
    @Min(value = 0, message = "El ID de Registro del estudiante debe ser un número positivo")
    private Long studentRecordId;
    @Schema(description = "Student's first names", example = "Juan")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El nombre no puede contener caracteres especiales")
    private String firstName;
    @Schema(description = "Grade", example = "5A")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El curso no puede contener caracteres especiales")
    private String gradeLevel;
    @Schema(description = "Home address", example = "Calle 106 # 18-40, Bogotá")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La dirección no puede contener caracteres especiales")
    private String homeAddress;
    @Schema(description = "Student's last names", example = "Pérez")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El apellido no puede contener caracteres especiales")
    private String lastName;
    @Schema(description = "Whether the student can leave the bus alone", example = "false")
    @NotNull
    private boolean leaveAlone;
    @Schema(description = "Whether the student can board the bus alone", example = "false")
    @NotNull
    private boolean pickUpAlone;
    @Schema(description = "Student RFID card tag", example = "E2003412B802011")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El tag RfID no puede contener caracteres especiales")
    private String rfidTag;
    @Schema(description = "Student school ID (student_id)", example = "20231045")
    @NotNull
    @Min(value = 1, message = "El ID de Colegio del estudiante debe ser un número positivo")
    private Long studentId;
    @Schema(description = "\"NO\" means no transport; other values mean yes", example = "SI")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El transporte no puede contener caracteres especiales")
    private String transport;
    @Schema(description = "Family code", example = "12345678")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El codigo de Familia no puede contener caracteres especiales")
    private String familyCode;
    @Schema(description = "School name", example = "Colegio San José")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El colegio no puede contener caracteres especiales")
    private String schoolName;
}
