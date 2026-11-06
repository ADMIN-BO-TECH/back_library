package co.com.botech.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicle response")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {

    @Schema(description = "ID")
    private Long id;
    @Schema(description = "Plate number")
    private String plateNumber;
    @Schema(description = "Fleet number")
    private String fleetNumber;
    @Schema(description = "RFID tag")
    private String rfidTag;

    // Datos generales
    @Schema(description = "Body type")
    private String bodyType;
    @Schema(description = "Engine displacement")
    private String engineDisplacement;
    @Schema(description = "Vehicle class")
    private String vehicleClass;
    @Schema(description = "Color")
    private String color;
    @Schema(description = "Fuel type")
    private String fuelType;
    @Schema(description = "Line")
    private String line;
    @Schema(description = "Brand")
    private String brand;
    @Schema(description = "Model year")
    private String modelYear;
    @Schema(description = "Chassis number")
    private String chassisNumber;
    @Schema(description = "Engine number")
    private String engineNumber;
    @Schema(description = "Serial number")
    private String serialNumber;
    @Schema(description = "Service type")
    private String serviceType;

    // Documentación legal
    @Schema(description = "Transit license number")
    private String transitLicenseNumber;
    @Schema(description = "Transit department")
    private String transitDepartment;
    @Schema(description = "Ownership")
    private String ownership;

    // SOAT
    @Schema(description = "SOAT expiration date")
    private LocalDate soatExpirationDate;
    @Schema(description = "SOAT policy number")
    private String soatPolicyNumber;

    // Tarjeta de operación
    @Schema(description = "Operation card expiration")
    private LocalDate operationCardExpiration;
    @Schema(description = "Operation card modality")
    private String operationCardModality;
    @Schema(description = "Operation card number")
    private String operationCardNumber;

    // Técnico-mecánica
    @Schema(description = "Mechanical inspection number")
    private String mechanicalInspectionNumber;
    @Schema(description = "Mechanical inspection expiration")
    private LocalDate mechanicalInspectionExpiration;

    // Kilometraje
    @Schema(description = "Mileage")
    private Integer mileage;
    @Schema(description = "Mileage last updated")
    private LocalDateTime mileageLastUpdated;

    // Capacidad y estado
    @Schema(description = "Vehicle capacity")
    private Integer vehicleCapacity;
    @Schema(description = "Vehicle status")
    private String vehicleStatus;
}
