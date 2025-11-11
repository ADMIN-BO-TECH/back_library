package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "notification_history")
public class NotificationHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;

    @Column(name = "email") private String email;
    @Column(name = "title") private String title;
    @Column(name = "body")  private String body;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "notification_category_id")
    private NotificationCategory category;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;
}
