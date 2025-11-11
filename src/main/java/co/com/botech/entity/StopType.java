package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "stop_type")
public class StopType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stop_types_id")
    private Long id;

    @Column(name = "stop_type_name")
    private String stopTypeName;
}
