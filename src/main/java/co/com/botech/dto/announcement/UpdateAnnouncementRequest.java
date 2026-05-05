package co.com.botech.dto.announcement;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateAnnouncementRequest {

    @Size(max = 150, message = "El título no puede superar 150 caracteres")
    private String title;

    @Size(max = 5000, message = "La descripción es demasiado larga")
    private String description;

    private List<
            @Size(max = 30, message = "Cada tag debe tener máximo 30 caracteres")
            @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
                    String
            > tags;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de publicación debe tener el formato yyyy-MM-dd")
    private String publishDate;

    @Min(value = 1, message = "El id del colegio debe ser un número positivo")
    private Long schoolId;

    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El estado no puede contener caracteres especiales")
    private String status;
}