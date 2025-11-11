package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "family_code")
public class FamilyCode {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "family_code_id")
    private Long id;

    @Column(name = "family_code", unique = true)
    private String code;
}