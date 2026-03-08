package co.com.botech.entity;

import co.com.botech.constants.UserType;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_family", uniqueConstraints = {@UniqueConstraint(name = "uk_user_family_user_family", columnNames = {"user_id", "family_id"})})
public class UserFamily {

    @EmbeddedId
    private UserFamilyId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("familyId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id", nullable = false)
    private Family family;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false, length = 30)
    private UserType userType;
}