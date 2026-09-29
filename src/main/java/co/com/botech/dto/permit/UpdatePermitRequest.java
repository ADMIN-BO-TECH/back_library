package co.com.botech.dto.permit;

import com.google.firebase.database.annotations.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePermitRequest {

    @Pattern(
            regexp = "^[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]+$",
            message = "El responsable contiene caracteres de control no permitidos"
    )
    private String repliedBy;

    @Pattern(
            regexp = "^[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]+$",
            message = "La respuesta contiene caracteres de control no permitidos"
    )
    private String response;

    @Pattern(
            regexp = "^[^\\x00-\\x08\\x0B\\x0C\\x0E-\\x1F\\x7F]+$",
            message = "El estado de permiso contiene caracteres de control no permitidos"
    )
    private String permitStatus;
}