package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "preoperations", indexes = {
        @Index(name = "idx_preoperations_preop_date", columnList = "preop_date"),
        @Index(name = "idx_preoperations_vehicle_date", columnList = "vehicle_id, preop_date")
})
public class Preoperation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    // Congelados al momento del registro: el vehiculo puede cambiar de placa despues,
    // y el reporte historico debe reflejar lo que era ese dia, no lo que es hoy.
    // La preoperacion es del cliente (tenant), no del colegio: los buses cubren rutas
    // de varios colegios, asi que aqui NO va ningun colegio/school_id.
    @Column(name = "plate_number", nullable = false)
    private String plateNumber;

    @Column(name = "fleet_number", nullable = false)
    private String fleetNumber;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "operator_name")
    private String operatorName;

    @Column(name = "operator_document")
    private String operatorDocument;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "preop_date", nullable = false)
    private LocalDate preopDate;

    @Column(name = "preop_hour", nullable = false)
    private LocalTime preopHour;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "accepted_veracity")
    private Boolean acceptedVeracity;

    @Column(name = "gps_lat")
    private Double gpsLat;

    @Column(name = "gps_lng")
    private Double gpsLng;

    @Column(name = "internal_notes", columnDefinition = "TEXT")
    private String internalNotes;

    @Column(name = "external_notes", columnDefinition = "TEXT")
    private String externalNotes;

    @Column(name = "kit_notes", columnDefinition = "TEXT")
    private String kitNotes;

    @Column(name = "documents_notes", columnDefinition = "TEXT")
    private String documentsNotes;

    @Column(name = "has_issues", nullable = false)
    private Boolean hasIssues;

    @Column(name = "has_critical", nullable = false)
    private Boolean hasCritical;

    @Column(name = "issue_count", nullable = false)
    private Integer issueCount;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
