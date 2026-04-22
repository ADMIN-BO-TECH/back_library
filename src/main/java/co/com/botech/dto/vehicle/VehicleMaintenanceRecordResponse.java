package co.com.botech.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMaintenanceRecordResponse {

    private Long id;
    private Long vehicleId;
    private String plateNumber;
    private String fleetNumber;
    private String maintenanceType;
    private String title;
    private String description;
    private String responsibleName;
    private String responsibleContact;
    private Integer mileageAtService;
    private BigDecimal cost;
    private LocalDate maintenanceDate;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
