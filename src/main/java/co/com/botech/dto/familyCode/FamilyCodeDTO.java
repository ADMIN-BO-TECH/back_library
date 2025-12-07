package co.com.botech.dto.familyCode;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FamilyCodeDTO {
    private Long familyCodeId;
    @NotNull
    private String familyCode;
}
