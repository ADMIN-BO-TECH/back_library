package co.com.botech.dto.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicle maintenance schedule")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMaintenanceScheduleResponse {

    @Schema(description = "ID")
    private Long id;
    @Schema(description = "Vehicle ID")
    private Long vehicleId;
    @Schema(description = "Plate number")
    private String plateNumber;
    @Schema(description = "Fleet number")
    private String fleetNumber;
    @Schema(description = "Scheduled date")
    private LocalDate scheduledDate;
    @Schema(description = "Title")
    private String title;
    @Schema(description = "Description")
    private String description;
    @Schema(description = "Status")
    private String status;
    @Schema(description = "Completed date")
    private LocalDate completedDate;
    @Schema(description = "Maintenance record ID")
    private Long maintenanceRecordId;
    @Schema(description = "Created at")
    private LocalDateTime createdAt;
    @Schema(description = "Updated at")
    private LocalDateTime updatedAt;
}