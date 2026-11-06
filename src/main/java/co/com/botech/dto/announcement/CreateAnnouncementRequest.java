package co.com.botech.dto.announcement;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "New school announcement")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateAnnouncementRequest {

    @Schema(description = "Announcement title", example = "Salida pedagógica")
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 150, message = "El título no puede superar 150 caracteres")
    private String title;

    @Schema(description = "Announcement content", example = "El viernes 24 de octubre los estudiantes de quinto saldrán a las 11:00 AM.")
    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 5000, message = "La descripción es demasiado larga")
    private String description;

    @Schema(description = "Announcement tags (letters, numbers, _ or -)", example = "[\"eventos\", \"primaria\"]")
    private List<@Size(max = 30, message = "Cada tag debe tener máximo 30 caracteres")@Pattern(regexp = "^[a-zA-Z0-9_-]+$",  message = "Hay tags con caracteres especiales") String> tags;

    @Schema(description = "Publish date (yyyy-MM-dd)", example = "2026-10-20")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de publicación debe tener el formato yyyy-MM-dd")
    private String publishDate;

    @Schema(description = "School ID", example = "1")
    @Min(value = 1, message = "El id del colegio debe ser un número positivo")
    private Long schoolId;

    @Schema(description = "Status: DRAFT, PUBLISHED or ARCHIVED", example = "DRAFT")
    @Pattern(regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$", message = "El estado no puede contener caracteres especiales")
    private String status;
}