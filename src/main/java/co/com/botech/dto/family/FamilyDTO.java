package co.com.botech.dto.family;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FamilyDTO {

    private Long familyId;
    @NotBlank
    private String familyCode;
    private String schoolName;
}