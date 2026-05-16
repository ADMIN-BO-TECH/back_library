package co.com.botech.dto.notification;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UpdateCategoryPreferenceDTO {
    private String categoryName;
    private Boolean enabled;
}
