package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "user_disabled_notification")
public class UserDisabledNotification {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_disabled_notification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "notification_category_id")
    private NotificationCategory notificationCategory;
}