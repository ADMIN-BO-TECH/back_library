package co.com.botech.dto.notification;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPreferenceResponse {
    private String categoryName;
    private String categoryDescription;
    private Boolean enabled;
}