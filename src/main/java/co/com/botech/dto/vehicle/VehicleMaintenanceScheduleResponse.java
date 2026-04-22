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
public class VehicleMaintenanceScheduleResponse {

    private Long id;
    private Long vehicleId;
    private String plateNumber;
    private String fleetNumber;
    private LocalDate scheduledDate;
    private String title;
    private String description;
    private String status;
    private LocalDate completedDate;
    private Long maintenanceRecordId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}