package co.com.botech.dto.linkManagement;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateLinkManagementRequest {

    @NotNull(message = "El campo visibleInApp es obligatorio")
    private Boolean visibleInApp;

    private String description;

    @NotNull(message = "El routeId es obligatorio")
    private Long routeId;
}