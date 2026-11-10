package co.com.botech.entity;

import co.com.botech.constants.MaintenanceOrderType;
import jakarta.persistence.*;
import lombok.*;

/** Catálogo ampliable de áreas afectadas. Las áreas se desactivan, no se borran. */
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "maintenance_affected_area",
        uniqueConstraints = @UniqueConstraint(name = "uk_maintenance_affected_area_code", columnNames = "code"))
public class MaintenanceAffectedArea {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "name", nullable = false, length = 120)
    private String name;

    // NULL = aplica a ambos tipos de orden
    @Enumerated(EnumType.STRING)
    @Column(name = "applies_to_type", columnDefinition = "varchar(30)")
    private MaintenanceOrderType appliesToType;

    @Builder.Default
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;

    @Builder.Default
    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean active = true;
}
