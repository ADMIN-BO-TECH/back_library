package co.com.botech.dto.schoolEmployee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "School employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolEmployeeResponse {

    @Schema(description = "First names", example = "Laura")
    private String name;
    @Schema(description = "Last names", example = "Gómez")
    private String lastName;
    @Schema(description = "Document type", example = "Cédula de ciudadanía")
    private String documentType;
    @Schema(description = "Document number", example = "1020304050")
    private String documentNumber;
    @Schema(description = "School position", example = "Coordinadora")
    private String position;
}