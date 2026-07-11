package co.com.botech.entity;

import co.com.botech.constants.GeofenceEventType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Registro auditable de notificaciones de entrada/salida de geocerca enviadas
 * (o intentadas) a un usuario. No usa NotificationHistory porque esa entidad
 * es genérica (email/title/body) y no tiene campos para bus, geocerca ni tipo
 * de evento — mezclar esos campos ahí afectaría a otras features que la usan.
 */
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "geofence_notification_log")
public class GeofenceNotificationLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "geofence_id", nullable = false)
    private Long geofenceId;

    @Column(name = "rfid_tag", nullable = false)
    private String rfidTag;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false, length = 10)
    private GeofenceEventType eventType;

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    @Column(name = "success", nullable = false)
    private Boolean success;
}
