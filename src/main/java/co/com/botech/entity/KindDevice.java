package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "kind_device")
public class KindDevice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kind_device_id")
    private Long id;

    @Column(name = "description")
    private String description;
}
