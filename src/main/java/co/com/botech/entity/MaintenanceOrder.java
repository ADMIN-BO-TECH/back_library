package co.com.botech.entity;

import co.com.botech.constants.MaintenanceOrderOrigin;
import co.com.botech.constants.MaintenanceOrderPriority;
import co.com.botech.constants.MaintenanceOrderSource;
import co.com.botech.constants.MaintenanceOrderStatus;
import co.com.botech.constants.MaintenanceOrderType;
import co.com.botech.constants.ProfileKindSSO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Orden de mantenimiento (OM): qué daño hay, cuándo se reportó y qué área afecta. No detalla
 * repuestos ni arreglos (eso es la orden de servicio, Fase 2, que apunta a la OM: N a 1).
 * No tiene colegio: el flujo de mantenimiento es independiente del colegio.
 *
 * El estado solo cambia con {@link #changeStatus}; no hay setter de {@code status}.
 */
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(name = "maintenance_order",
        uniqueConstraints = @UniqueConstraint(name = "uk_maintenance_order_code", columnNames = "code"),
        indexes = {
                @Index(name = "idx_mo_status_requested", columnList = "status, requested_at"),
                @Index(name = "idx_mo_vehicle_area_status", columnList = "vehicle_id, affected_area_id, status"),
                @Index(name = "idx_mo_requester", columnList = "requested_by_user_id, requested_at"),
                @Index(name = "idx_mo_preoperation", columnList = "preoperation_id")
        })
public class MaintenanceOrder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // OM-AAAA-NNNN, inmutable
    @Column(name = "code", nullable = false, updatable = false, length = 20)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, columnDefinition = "varchar(30)")
    private MaintenanceOrderType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "affected_area_id", nullable = false)
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private MaintenanceAffectedArea affectedArea;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, columnDefinition = "varchar(30)")
    private MaintenanceOrderPriority priority = MaintenanceOrderPriority.NORMAL;

    @Enumerated(EnumType.STRING)
    @Column(name = "origin", nullable = false, columnDefinition = "varchar(30)")
    private MaintenanceOrderOrigin origin;

    @Enumerated(EnumType.STRING)
    @Column(name = "source", nullable = false, columnDefinition = "varchar(30)")
    private MaintenanceOrderSource source;

    // Obligatoria si y solo si source = PREOPERACION (validación de servicio)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preoperation_id")
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Preoperation preoperation;

    // Sub del JWT. Sin FK a users: User tiene @SQLRestriction("enabled = true").
    @Column(name = "requested_by_user_id", nullable = false, updatable = false)
    private Long requestedByUserId;

    // Perfil con el que se solicitó. Se guarda el nombre de la constante: varchar(40) porque
    // COORD_MANTENIMIENTO_SEGUIMIENTO mide 31 caracteres.
    @Enumerated(EnumType.STRING)
    @Column(name = "requested_by_role", nullable = false, updatable = false, columnDefinition = "varchar(40)")
    private ProfileKindSSO requestedByRole;

    @Column(name = "decided_by_user_id")
    private Long decidedByUserId;

    @Enumerated(EnumType.STRING)
    @Column(name = "decided_by_role", columnDefinition = "varchar(40)")
    private ProfileKindSSO decidedByRole;

    @Column(name = "decided_at")
    private LocalDateTime decidedAt;

    @Column(name = "decision_reason", columnDefinition = "TEXT")
    private String decisionReason;

    // Solo cambia vía changeStatus (lo invoca únicamente el servicio de transiciones).
    @Builder.Default
    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "varchar(30)")
    private MaintenanceOrderStatus status = MaintenanceOrderStatus.SOLICITADA;

    @Column(name = "requested_at", nullable = false, updatable = false)
    private LocalDateTime requestedAt;

    // Fecha tentativa opcional al posponer; se limpia al salir de POSPUESTA
    @Column(name = "postponed_until")
    private LocalDateTime postponedUntil;

    // Copia desnormalizada del evento CORRECTIVO vigente del cronograma (T2)
    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @Column(name = "entered_shop_at")
    private LocalDateTime enteredShopAt;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @Builder.Default
    @Column(name = "active", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean active = true;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /** Aplica un cambio de estado validando la matriz de {@link MaintenanceOrderStatus}. */
    public void changeStatus(MaintenanceOrderStatus next) {
        if (!status.canTransitionTo(next)) {
            throw new IllegalStateException("Transición de estado no permitida: " + status + " -> " + next);
        }
        this.status = next;
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.requestedAt == null) {
            this.requestedAt = now;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
