package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "notification_channel")
public class NotificationChannel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_channel_id")
    private Long id;

    @Column(name = "channel_name")
    private String channelName;

    @Column(name = "global_enabled")
    private Boolean globalEnabled;
}