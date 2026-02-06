package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name") private String firstName;
    @Column(name = "last_name")  private String lastName;

    @Column(name = "fcm_token")  private String fcmToken;

    @Column(name = "firebase_uid")
    private String firebaseUid;
}