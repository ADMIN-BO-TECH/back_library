package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_rfid_assignment",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_user_rfid",
                columnNames = {"user_id", "rfid_register_id"}
        ))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRfidAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rfid_register_id", nullable = false)
    private RfidRegister rfidRegister;
}