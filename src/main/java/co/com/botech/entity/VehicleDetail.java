package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "vehicles_details")
public class VehicleDetail {
    @Id
    @Column(name = "vehicle_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) @MapsId
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    // ── Datos generales del vehículo ──────────────────────────────────
    @Column(name = "body_type")                      private String bodyType;
    @Column(name = "engine_displacement")             private String engineDisplacement;
    @Column(name = "vehicle_class")                   private String vehicleClass;
    @Column(name = "color")                           private String color;
    @Column(name = "fuel_type")                       private String fuelType;
    @Column(name = "line")                            private String line;
    @Column(name = "brand")                           private String brand;
    @Column(name = "model_year")                      private String modelYear;
    @Column(name = "chassis_number")                  private String chassisNumber;
    @Column(name = "engine_number")                   private String engineNumber;
    @Column(name = "serial_number")                   private String serialNumber;
    @Column(name = "service_type")                    private String serviceType;

    // ── Documentación legal ───────────────────────────────────────────
    @Column(name = "transit_license_number")          private String transitLicenseNumber;
    @Column(name = "transit_department")              private String transitDepartment;
    @Column(name = "ownership")                       private String ownership;

    // ── SOAT ──────────────────────────────────────────────────────────
    @Column(name = "soat_expiration_date")            private LocalDate soatExpirationDate;
    @Column(name = "soat_policy_number")              private String soatPolicyNumber;

    // ── Tarjeta de operación ──────────────────────────────────────────
    @Column(name = "operation_card_expiration")       private LocalDate operationCardExpiration;
    @Column(name = "operation_card_modality")         private String operationCardModality;
    @Column(name = "operation_card_number")           private String operationCardNumber;

    // ── Revisión técnico-mecánica ─────────────────────────────────────
    @Column(name = "mechanical_inspection_number")    private String mechanicalInspectionNumber;
    @Column(name = "mechanical_inspection_expiration") private LocalDate mechanicalInspectionExpiration;

    // ── Kilometraje ───────────────────────────────────────────────────
    @Column(name = "mileage")                         private Integer mileage;
    @Column(name = "mileage_last_updated")            private LocalDateTime mileageLastUpdated;

    // ── Capacidad y estado ────────────────────────────────────────────
    @Column(name = "vehicle_capacity")                private Integer vehicleCapacity;
    @Column(name = "vehicle_status")                  private String vehicleStatus;
}