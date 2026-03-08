package co.com.botech.dto.authorization;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAuthorizationRequest {
    @NonNull
    @Min(value = 1, message = "El id del Permiso debe ser un número positivo")
    private Long idAuthorization;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de inicio debe tener el formato yyyy-MM-dd")
    private String authorizationStart;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha fin debe tener el formato yyyy-MM-dd")
    private String authorizationEnd;
}
