package co.com.botech.dto.notification;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteNotificationRequest {
    @NotEmpty(message = "La lista de ids de recorridos no puede estar vacía")
    private List<Long> routeIdsList;

    @Valid
    @NotNull(message = "El cuerpo de la notificación es obligatorio")
    private NotificationBodyRequest request;
}
