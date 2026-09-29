package co.com.botech.dto.permit;

import com.google.firebase.database.annotations.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePermitRequest {

    @NotBlank
    @NotNull
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Pattern(
            regexp = "^[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]+$",
            message = "La descripción contiene caracteres de control no permitidos"
    )
    private String description;

    @NotNull
    private LocalDate permitDate;

    @NotNull
    @NotEmpty
    private List<Long> studentIds;

    @NotBlank
    @NotNull
    @Size(max = 200, message = "El campo 'solicitado por' no puede superar los 200 caracteres")
    @Pattern(
            regexp = "^[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]+$",
            message = "El campo 'solicitado por' contiene caracteres de control no permitidos"
    )
    private String requestedBy;

    @NotBlank
    @NotNull
    private String permitType;
}