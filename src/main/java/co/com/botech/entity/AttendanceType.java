package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "attendance_type")
public class AttendanceType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_type_id")
    private Long id;

    @Column(name = "description")
    private String description; // Entrada Portería, Salida Portería, Subida Bus, Bajada Bus
}
