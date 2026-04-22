package co.com.botech.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSummaryResponse {

    private Long vehicleId;
    private String plateNumber;
    private String fleetNumber;
    private String brand;
    private String line;
    private String modelYear;
    private String color;
    private String bodyType;
    private String vehicleClass;
    private String fuelType;
    private Integer vehicleCapacity;
    private Integer mileage;
    private LocalDateTime mileageLastUpdated;
    private String vehicleStatus;

    private LocalDate soatExpirationDate;
    private Integer soatDaysRemaining;

    private LocalDate mechanicalInspectionExpiration;
    private Integer mechanicalInspectionDaysRemaining;

    private LocalDate operationCardExpiration;
    private Integer operationCardDaysRemaining;

    private LocalDate nextScheduledMaintenance;
}