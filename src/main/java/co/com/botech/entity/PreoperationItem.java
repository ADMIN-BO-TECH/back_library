package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "preoperation_items", indexes = {
        @Index(name = "idx_preop_items_preoperation", columnList = "preoperation_id"),
        @Index(name = "idx_preop_items_category_key_status", columnList = "category, item_key, status")
})
public class PreoperationItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preoperation_id", nullable = false)
    private Preoperation preoperation;

    // EXTERNA, INTERNA, KIT, DOCUMENTO
    @Column(name = "category", nullable = false)
    private String category;

    // p.ej. "llantas_traseras", "fuga_aceite", "extintor_vigencia", "soat"
    @Column(name = "item_key", nullable = false)
    private String itemKey;

    // valor normalizado: bueno/malo/danado, SI/NO, si/no/novedad, vigente/no_vigente
    @Column(name = "status", nullable = false)
    private String status;

    // solo presente en los campos de fuga de INTERNA cuando hay fuga: NORMAL | BAJO
    @Column(name = "fluid_level")
    private String fluidLevel;

    @Column(name = "is_issue", nullable = false)
    private Boolean isIssue;

    @Column(name = "is_critical", nullable = false)
    private Boolean isCritical;
}
