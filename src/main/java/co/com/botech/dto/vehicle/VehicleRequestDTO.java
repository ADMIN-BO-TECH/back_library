package co.com.botech.dto.vehicle;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicle general and legal data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequestDTO {

    @Schema(description = "Vehicle ID; empty when creating", example = "8")
    private Long id;

    @Schema(description = "License plate", example = "ABC123")
    @NotBlank
    private String plateNumber;
    @Schema(description = "Vehicle fleet number", example = "12")
    @NotBlank
    private String fleetNumber;
    @Schema(description = "Vehicle RFID tag", example = "E2003412B802099")
    private String rfidTag;

    // Datos generales
    @Schema(description = "Body type", example = "Cerrada")
    private String bodyType;
    @Schema(description = "Engine displacement", example = "4000")
    private String engineDisplacement;
    @Schema(description = "Vehicle class", example = "Bus")
    private String vehicleClass;
    @Schema(description = "Color", example = "Amarillo")
    private String color;
    @Schema(description = "Fuel type", example = "Diésel")
    private String fuelType;
    @Schema(description = "Vehicle line", example = "NPR")
    private String line;
    @Schema(description = "Brand", example = "Chevrolet")
    private String brand;
    @Schema(description = "Model year", example = "2022")
    private String modelYear;
    @Schema(description = "Chassis number", example = "9GCNPR75XNB123456")
    private String chassisNumber;
    @Schema(description = "Engine number", example = "4HK1-123456")
    private String engineNumber;
    @Schema(description = "Serial number", example = "SN-0012345")
    private String serialNumber;
    @Schema(description = "Service type", example = "Especial")
    private String serviceType;

    // Documentación legal
    @Schema(description = "Transit license number", example = "10012345678")
    private String transitLicenseNumber;
    @Schema(description = "Transit authority", example = "Secretaría de Movilidad de Bogotá")
    private String transitDepartment;
    @Schema(description = "Owner or ownership type", example = "Propio")
    private String ownership;

    // SOAT
    @Schema(description = "SOAT expiration date", example = "2027-03-31")
    private LocalDate soatExpirationDate;
    @Schema(description = "SOAT policy number", example = "12345678")
    private String soatPolicyNumber;

    // Tarjeta de operación
    @Schema(description = "Operation card expiration date", example = "2027-06-30")
    private LocalDate operationCardExpiration;
    @Schema(description = "Operation card modality", example = "Especial")
    private String operationCardModality;
    @Schema(description = "Operation card number", example = "TO-123456")
    private String operationCardNumber;

    // Técnico-mecánica
    @Schema(description = "Technical inspection certificate number", example = "RTM-987654")
    private String mechanicalInspectionNumber;
    @Schema(description = "Technical inspection expiration date", example = "2027-01-31")
    private LocalDate mechanicalInspectionExpiration;

    // Kilometraje
    @Schema(description = "Current mileage", example = "85400")
    private Integer mileage;

    // Capacidad y estado
    @Schema(description = "Passenger capacity", example = "30")
    private Integer vehicleCapacity;
    @Schema(description = "Free text. Used values: OPERATIVO, REQUIERE_ATENCION, NO_OPERATIVO", example = "OPERATIVO")
    private String vehicleStatus;
}