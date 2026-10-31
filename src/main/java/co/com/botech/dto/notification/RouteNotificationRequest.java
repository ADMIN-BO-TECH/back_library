package co.com.botech.dto.notification;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Notification to parents by route")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteNotificationRequest {
    @Schema(description = "Route IDs to notify", example = "[12, 13]")
    @NotEmpty(message = "La lista de ids de recorridos no puede estar vacía")
    private List<Long> routeIdsList;

    @Schema(description = "Notification content")
    @Valid
    @NotNull(message = "El cuerpo de la notificación es obligatorio")
    private NotificationBodyRequest request;
}
