package co.com.botech.entity;

import co.com.botech.constants.MaintenanceOrderStatus;
import co.com.botech.constants.ProfileKindSSO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

/**
 * Historial de estados de la OM. Tabla inmutable: solo INSERT. Sin setters a propósito, y el
 * repositorio no expone update ni delete. Se escribe en la misma transacción del cambio de estado.
 */
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
@Immutable
@Entity
@Table(name = "maintenance_order_status_history",
        indexes = @Index(name = "idx_mosh_order_changed", columnList = "maintenance_order_id, changed_at"))
public class MaintenanceOrderStatusHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maintenance_order_id", nullable = false, updatable = false)
    @ToString.Exclude
    private MaintenanceOrder maintenanceOrder;

    // NULL en el registro de creación
    @Enumerated(EnumType.STRING)
    @Column(name = "from_status", updatable = false, columnDefinition = "varchar(30)")
    private MaintenanceOrderStatus fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_status", nullable = false, updatable = false, columnDefinition = "varchar(30)")
    private MaintenanceOrderStatus toStatus;

    // NULL solo cuando el rol es ProfileKindSSO.SISTEMA
    @Column(name = "changed_by_user_id", updatable = false)
    private Long changedByUserId;

    // varchar(40): se guarda el nombre de la constante (COORD_MANTENIMIENTO_SEGUIMIENTO mide 31)
    @Enumerated(EnumType.STRING)
    @Column(name = "changed_by_role", nullable = false, updatable = false, columnDefinition = "varchar(40)")
    private ProfileKindSSO changedByRole;

    @Column(name = "changed_at", nullable = false, updatable = false)
    private LocalDateTime changedAt;

    @Column(name = "comment", updatable = false, columnDefinition = "TEXT")
    private String comment;

    @PrePersist
    protected void onCreate() {
        if (this.changedAt == null) {
            this.changedAt = LocalDateTime.now();
        }
    }
}
