package co.com.botech.dto.linkManagement;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateLinkManagementRequest {
    @NotNull(message = "El linkId es obligatorio")
    private Long linkId;
    @NotNull(message = "El campo visibleInApp es obligatorio")
    private Boolean visibleInApp;
    private String description;
}

