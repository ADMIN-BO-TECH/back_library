package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "vehicle_maintenance_record")
public class VehicleMaintenanceRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    // PREVENTIVO, CORRECTIVO, EMERGENCIA
    @Column(name = "maintenance_type", nullable = false)
    private String maintenanceType;

    // Resumen corto: "Cambio de aceite", "Reparación frenos"
    @Column(name = "title", nullable = false)
    private String title;

    // Detalle libre de lo que se hizo
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // Responsable del mantenimiento (taller o persona)
    @Column(name = "responsible_name")
    private String responsibleName;

    // Contacto del responsable
    @Column(name = "responsible_contact")
    private String responsibleContact;

    // Kilometraje al momento del mantenimiento
    @Column(name = "mileage_at_service")
    private Integer mileageAtService;

    // Costo del mantenimiento (opcional)
    @Column(name = "cost", precision = 12, scale = 2)
    private BigDecimal cost;

    // Fecha en que se realizó
    @Column(name = "maintenance_date", nullable = false)
    private LocalDate maintenanceDate;

    // Observaciones adicionales
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}