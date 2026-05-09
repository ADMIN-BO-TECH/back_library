package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "user_disabled_categories")
public class UserDisabledCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_disabled_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "notification_category_id")
    private NotificationCategory notificationCategory;
}