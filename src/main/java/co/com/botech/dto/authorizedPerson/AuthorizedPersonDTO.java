package co.com.botech.dto.authorizedPerson;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authorized relative, provider or guest")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorizedPersonDTO {
    @Schema(description = "Authorized person ID", example = "88")
    @Min(value = 1, message = "El id de la persona autorizada debe ser un número positivo")
    private Long authorizedPersonId;
    @Schema(description = "Document type ID", example = "1")
    @NotNull
    @Min(value = 1, message = "El id del tipo de documento debe ser un número positivo")
    private Long documentTypeId;
    @Schema(description = "Document number", example = "1020304050")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El numero de documento contiene caracteres inválidos")
    private String documentNumber;
    @Schema(description = "Authorized person's first names", example = "Carlos")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "Los nombres contienen caracteres inválidos")
    private String firstName;
    @Schema(description = "Authorized person's last names", example = "Rodríguez")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "Los apellidos contienen caracteres inválidos")
    private String lastName;
    @Schema(description = "Name of person granting authorization", example = "María Pérez")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La persona que autoriza contienen caracteres inválidos")
    private String authorizedBy;
    @Schema(description = "Family code; required for Familiar, empty or \"Externo\" otherwise", example = "12345678")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]*$",
            message = "El codigo de familia contiene caracteres inválidos")
    private String familyCode;
    @Schema(description = "Created date and time", example = "2026-10-20T07:30:00")
    private LocalDateTime createdAt;
    @Schema(description = "Last updated date and time", example = "2026-10-21T09:15:00")
    private LocalDateTime updatedAt;
    @Schema(description = "Start date (yyyy-MM-dd); cannot be past", example = "2026-11-03")
    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de inicio debe tener el formato yyyy-MM-dd")
    private String authorizationStart;
    @Schema(description = "End date (yyyy-MM-dd); cannot be before start", example = "2026-11-30")
    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha fin debe tener el formato yyyy-MM-dd")
    private String authorizationEnd;
    @Schema(description = "Authorization reason or details", example = "Recoge al estudiante los viernes")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El codigo de familia contiene caracteres inválidos")
    private String description;
    @Schema(description = "Allowed: Proveedor, Familiar, Invitado. Only Familiar has a family code", example = "Familiar")
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El codigo de familia contiene caracteres inválidos")
    private String type;
    @Schema(description = "School name", example = "Colegio San José")
    private String schoolName;
}
