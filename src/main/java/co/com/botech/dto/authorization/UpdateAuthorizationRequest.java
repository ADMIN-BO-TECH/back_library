package co.com.botech.dto.authorization;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authorization date update")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAuthorizationRequest {
    @Schema(description = "Authorization ID", example = "57")
    @NonNull
    @Min(value = 1, message = "El id del Permiso debe ser un número positivo")
    private Long idAuthorization;
    @Schema(description = "Start date (yyyy-MM-dd)", example = "2026-10-20")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de inicio debe tener el formato yyyy-MM-dd")
    private String authorizationStart;
    @Schema(description = "End date (yyyy-MM-dd)", example = "2026-10-31")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha fin debe tener el formato yyyy-MM-dd")
    private String authorizationEnd;
}
