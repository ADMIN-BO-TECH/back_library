package co.com.botech.entity;

import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "vehicles_details")
public class VehicleDetail {
    @Id
    @Column(name = "vehicle_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) @MapsId
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Column(name="body_type")               private String bodyType;
    @Column(name="engine_displacement")     private String engineDisplacement;
    @Column(name="vehicle_class")           private String vehicleClass;
    @Column(name="color")                   private String color;
    @Column(name="fuel_type")               private String fuelType;
    @Column(name="line")                    private String line;
    @Column(name="brand")                   private String brand;
    @Column(name="model_year")              private String modelYear;
    @Column(name="chassis_number")          private String chassisNumber;
    @Column(name="transit_license_number")  private String transitLicenseNumber;
    @Column(name="engine_number")           private String engineNumber;
    @Column(name="serial_number")           private String serialNumber;
    @Column(name="ownership")               private String ownership;
    @Column(name="mechanical_inspection_number") private String mechanicalInspectionNumber;
    @Column(name="transit_department")      private String transitDepartment;
    @Column(name="soat_expiration_date")    private String soatExpirationDate;
    @Column(name="soat_policy_number")      private String soatPolicyNumber;
    @Column(name="service_type")            private String serviceType;
    @Column(name="operation_card_expiration") private String operationCardExpiration;
    @Column(name="operation_card_modality") private String operationCardModality;
    @Column(name="operation_card_number")   private String operationCardNumber;
}
