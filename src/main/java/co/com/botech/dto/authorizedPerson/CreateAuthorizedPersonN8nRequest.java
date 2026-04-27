package co.com.botech.dto.authorizedPerson;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class CreateAuthorizedPersonN8nRequest {

    @NotNull
    @Min(value = 1, message = "El id del tipo de documento debe ser positivo")
    private Long documentTypeId;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El número de documento contiene caracteres inválidos")
    private String documentNumber;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El nombre contiene caracteres inválidos")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "La fecha de inicio debe tener formato yyyy-MM-dd")
    private String authorizationStart;

    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "La fecha fin debe tener formato yyyy-MM-dd")
    private String authorizationEnd;
}