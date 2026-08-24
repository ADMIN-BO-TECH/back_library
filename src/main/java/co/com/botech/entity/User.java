
package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@SQLRestriction("enabled = true")
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name") private String firstName;
    @Column(name = "last_name")  private String lastName;
    @Column(name = "fcm_token")  private String fcmToken;
    @Column(name = "firebase_uid") private String firebaseUid;

    @Column(name = "enabled", nullable = false)
    @Builder.Default
    private boolean enabled = true;
}
