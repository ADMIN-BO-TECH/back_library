package co.com.botech.dto.family;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "School family")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FamilyDTO {

    @Schema(description = "Family ID", example = "301")
    private Long familyId;
    @Schema(description = "Family code", example = "12345678")
    @NotBlank
    private String familyCode;
    @Schema(description = "School name", example = "Colegio San José")
    private String schoolName;
}