package co.com.botech.dto.permit;

import com.google.firebase.database.annotations.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePermitRequest {
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "La descripción contiene caracteres inválidos")
    private String description;
    @NotNull
    private LocalDate permitDate;
    @NotNull
    @NotEmpty
    private List<Long> studentIds;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "Solicitado por contiene caracteres inválidos")
    private String requestedBy;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El tipo de permiso por contiene caracteres inválidos")
    private String permitType;
}
