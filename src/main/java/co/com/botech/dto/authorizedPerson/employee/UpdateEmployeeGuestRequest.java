package co.com.botech.dto.authorizedPerson.employee;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

/**
 * El número de documento no es editable: para cambiarlo se elimina la invitación y se crea una nueva.
 * Si la persona es compartida (isPersonDataEditable = false) los datos personales deben enviarse sin cambios.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmployeeGuestRequest {

    @NotNull
    @Min(value = 1, message = "El id del tipo de documento debe ser un número positivo")
    private Long documentTypeId;

    @NotBlank
    @Size(max = 100, message = "Los nombres no pueden superar los 100 caracteres")
    @Pattern(regexp = "^[\\p{L}0-9 .'-]+$", message = "Los nombres contienen caracteres inválidos")
    private String firstName;

    @NotBlank
    @Size(max = 100, message = "Los apellidos no pueden superar los 100 caracteres")
    @Pattern(regexp = "^[\\p{L}0-9 .'-]+$", message = "Los apellidos contienen caracteres inválidos")
    private String lastName;

    @NotNull
    private LocalDate authorizationStart;

    @NotNull
    private LocalDate authorizationEnd;

    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    @Pattern(
            regexp = "^[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]*$",
            message = "La descripción contiene caracteres de control no permitidos"
    )
    private String description;
}
