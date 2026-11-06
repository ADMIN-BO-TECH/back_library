package co.com.botech.dto.school;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "School")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SchoolDTO {
    @Schema(description = "School ID", example = "1")
    private Long id;
    @Schema(description = "School name", example = "Colegio San José")
    @NotNull
    private String name;
}
