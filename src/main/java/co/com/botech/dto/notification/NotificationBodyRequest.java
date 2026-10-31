package co.com.botech.dto.notification;


import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Push notification content")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationBodyRequest {
    @Schema(description = "Notification title", example = "Retraso en la ruta")
    private String title;
    @Schema(description = "Notification message", example = "El bus de la ruta 12 va con 15 minutos de retraso")
    private String body;
    @Schema(description = "Not used", example = "coordinacion@colegio.edu.co")
    private String email;
    @Schema(description = "Not used; manual notifications use \"Comunicados de coordinadores\"", example = "Comunicados de coordinadores")
    private String notificationCategory;
}
