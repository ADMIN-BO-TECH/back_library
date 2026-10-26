package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "school")
public class School {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(nullable = false, length = 60)
    private String code;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
}