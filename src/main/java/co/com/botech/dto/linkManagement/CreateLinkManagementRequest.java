package co.com.botech.dto.linkManagement;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CreateLinkManagementRequest {
    @NotBlank(message = "La URL del link es obligatoria")
    @Pattern(
            regexp = "^(https?|http)://[^\\s/$.?#].[^\\s]*$",
            message = "La URL del link debe iniciar con http:// o https://"
    )
    private String linkUrl;

    @NotNull(message = "El campo visibleInApp es obligatorio")
    private Boolean visibleInApp;

    @NotBlank(message = "La descripción es obligatoria")
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/\\r\\n-:]+$",
            message = "La descripción contiene caracteres inválidos"
    )
    private String description;

    @NotBlank(message = "La clave de acceso es obligatoria")
    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/\\r\\n-:]+$",
            message = "La clave de acceso solo puede contener letras, números, _ y -"
    )
    private String accessKey;

    @NotNull(message = "La ruta es obligatoria")
    @Positive(message = "El ID de la ruta debe ser un número positivo")
    private Long routeId;
}

