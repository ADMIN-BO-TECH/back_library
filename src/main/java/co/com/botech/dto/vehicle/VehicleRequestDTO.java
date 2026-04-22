package co.com.botech.dto.vehicle;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequestDTO {

    private Long id;

    @NotBlank
    private String plateNumber;
    @NotBlank
    private String fleetNumber;
    private String rfidTag;

    // Datos generales
    private String bodyType;
    private String engineDisplacement;
    private String vehicleClass;
    private String color;
    private String fuelType;
    private String line;
    private String brand;
    private String modelYear;
    private String chassisNumber;
    private String engineNumber;
    private String serialNumber;
    private String serviceType;

    // Documentación legal
    private String transitLicenseNumber;
    private String transitDepartment;
    private String ownership;

    // SOAT
    private LocalDate soatExpirationDate;
    private String soatPolicyNumber;

    // Tarjeta de operación
    private LocalDate operationCardExpiration;
    private String operationCardModality;
    private String operationCardNumber;

    // Técnico-mecánica
    private String mechanicalInspectionNumber;
    private LocalDate mechanicalInspectionExpiration;

    // Kilometraje
    private Integer mileage;

    // Capacidad y estado
    private Integer vehicleCapacity;
    private String vehicleStatus;
}