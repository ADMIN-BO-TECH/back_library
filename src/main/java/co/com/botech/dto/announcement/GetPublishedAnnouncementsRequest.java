package co.com.botech.dto.announcement;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetPublishedAnnouncementsRequest {

    @Min(value = 0, message = "La página no puede ser menor a 0")
    private int page;

    @Min(value = 1, message = "El límite debe ser mayor a 0")
    private int limit;

    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El tag no puede contener caracteres especiales")
    private String filteredTag;
}
