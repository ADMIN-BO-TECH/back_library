package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "vehicle_maintenance_schedule")
public class VehicleMaintenanceSchedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    // Fecha programada del mantenimiento
    @Column(name = "scheduled_date", nullable = false)
    private LocalDate scheduledDate;

    // Título descriptivo: "Mantenimiento mensual", "Revisión frenos"
    @Column(name = "title", nullable = false)
    private String title;

    // Descripción adicional de lo que se debe hacer
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // PENDIENTE, COMPLETADO, VENCIDO, CANCELADO
    @Column(name = "status", nullable = false)
    private String status;

    // Fecha en que se completó realmente (nullable hasta que se complete)
    @Column(name = "completed_date")
    private LocalDate completedDate;

    // Vínculo al registro de mantenimiento cuando se completa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_record_id")
    private VehicleMaintenanceRecord maintenanceRecord;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "PENDIENTE";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}