package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "notification_category")
public class NotificationCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_category_id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;
}
