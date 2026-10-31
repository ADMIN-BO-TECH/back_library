package co.com.botech.dto.authorizedPerson.employee;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "New guest authorized by a school employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEmployeeGuestRequest {

    @Schema(description = "Document type ID", example = "1")
    @NotNull
    @Min(value = 1, message = "El id del tipo de documento debe ser un número positivo")
    private Long documentTypeId;

    @Schema(description = "Document number", example = "1020304050")
    @NotBlank
    @Size(max = 50, message = "El número de documento no puede superar los 50 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "El numero de documento contiene caracteres inválidos")
    private String documentNumber;

    @Schema(description = "Guest's first names", example = "Carlos")
    @NotBlank
    @Size(max = 100, message = "Los nombres no pueden superar los 100 caracteres")
    @Pattern(regexp = "^[\\p{L}0-9 .'-]+$", message = "Los nombres contienen caracteres inválidos")
    private String firstName;

    @Schema(description = "Guest's last names", example = "Rodríguez")
    @NotBlank
    @Size(max = 100, message = "Los apellidos no pueden superar los 100 caracteres")
    @Pattern(regexp = "^[\\p{L}0-9 .'-]+$", message = "Los apellidos contienen caracteres inválidos")
    private String lastName;

    @Schema(description = "Authorization start date", example = "2026-10-20")
    @NotNull
    private LocalDate authorizationStart;

    @Schema(description = "Authorization end date", example = "2026-10-20")
    @NotNull
    private LocalDate authorizationEnd;

    @Schema(description = "Visit reason", example = "Reunión con coordinación")
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    @Pattern(
            regexp = "^[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]*$",
            message = "La descripción contiene caracteres de control no permitidos"
    )
    private String description;
}
