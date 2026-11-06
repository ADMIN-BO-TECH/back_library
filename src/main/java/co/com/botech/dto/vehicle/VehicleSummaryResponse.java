package co.com.botech.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicle summary")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSummaryResponse {

    @Schema(description = "Vehicle ID")
    private Long vehicleId;
    @Schema(description = "Plate number")
    private String plateNumber;
    @Schema(description = "Fleet number")
    private String fleetNumber;
    @Schema(description = "Brand")
    private String brand;
    @Schema(description = "Line")
    private String line;
    @Schema(description = "Model year")
    private String modelYear;
    @Schema(description = "Color")
    private String color;
    @Schema(description = "Body type")
    private String bodyType;
    @Schema(description = "Vehicle class")
    private String vehicleClass;
    @Schema(description = "Fuel type")
    private String fuelType;
    @Schema(description = "Vehicle capacity")
    private Integer vehicleCapacity;
    @Schema(description = "Mileage")
    private Integer mileage;
    @Schema(description = "Mileage last updated")
    private LocalDateTime mileageLastUpdated;
    @Schema(description = "Vehicle status")
    private String vehicleStatus;

    @Schema(description = "SOAT expiration date")
    private LocalDate soatExpirationDate;
    @Schema(description = "SOAT days remaining")
    private Integer soatDaysRemaining;

    @Schema(description = "Mechanical inspection expiration")
    private LocalDate mechanicalInspectionExpiration;
    @Schema(description = "Mechanical inspection days remaining")
    private Integer mechanicalInspectionDaysRemaining;

    @Schema(description = "Operation card expiration")
    private LocalDate operationCardExpiration;
    @Schema(description = "Operation card days remaining")
    private Integer operationCardDaysRemaining;

    @Schema(description = "Next scheduled maintenance")
    private LocalDate nextScheduledMaintenance;
}