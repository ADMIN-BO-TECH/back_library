package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "family", uniqueConstraints = {@UniqueConstraint(name = "uk_family_school_code", columnNames = {"school_id", "family_code"})})
public class Family {
    @Id
    @Column(name = "family_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @Column(name = "family_code", nullable = false, length = 255)
    private String familyCode;
}