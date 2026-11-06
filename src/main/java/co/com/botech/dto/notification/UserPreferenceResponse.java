package co.com.botech.dto.notification;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User preference")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPreferenceResponse {
    @Schema(description = "Category name")
    private String categoryName;
    @Schema(description = "Category description")
    private String categoryDescription;
    @Schema(description = "Enabled")
    private Boolean enabled;
}