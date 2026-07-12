package co.com.botech.entity;

import co.com.botech.constants.GeofenceEventType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "geofence_notification")
public class GeofenceNotification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
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
