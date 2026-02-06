package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserFamilyId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "family_id")
    private Long familyId;
}