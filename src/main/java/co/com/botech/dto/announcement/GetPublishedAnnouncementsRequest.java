package co.com.botech.dto.announcement;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Published announcements search")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetPublishedAnnouncementsRequest {

    @Schema(description = "Page number, starts at 1", example = "1")
    @Min(value = 0, message = "La página no puede ser menor a 0")
    private int page;

    @Schema(description = "Records per page", example = "10")
    @Min(value = 1, message = "El límite debe ser mayor a 0")
    private int limit;

    @Schema(description = "Tag filter", example = "eventos")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El tag no puede contener caracteres especiales")
    private String filteredTag;
}
