package co.com.botech.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicle maintenance record")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMaintenanceRecordResponse {

    @Schema(description = "ID")
    private Long id;
    @Schema(description = "Vehicle ID")
    private Long vehicleId;
    @Schema(description = "Plate number")
    private String plateNumber;
    @Schema(description = "Fleet number")
    private String fleetNumber;
    @Schema(description = "Maintenance type")
    private String maintenanceType;
    @Schema(description = "Title")
    private String title;
    @Schema(description = "Description")
    private String description;
    @Schema(description = "Responsible name")
    private String responsibleName;
    @Schema(description = "Responsible contact")
    private String responsibleContact;
    @Schema(description = "Mileage at service")
    private Integer mileageAtService;
    @Schema(description = "Cost")
    private BigDecimal cost;
    @Schema(description = "Maintenance date")
    private LocalDate maintenanceDate;
    @Schema(description = "Notes")
    private String notes;
    @Schema(description = "Created at")
    private LocalDateTime createdAt;
    @Schema(description = "Updated at")
    private LocalDateTime updatedAt;
}
