package co.com.botech.dto.notification;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UpdateCategoryPreferenceRequest {
    private String categoryName;
    private Boolean enabled;
}