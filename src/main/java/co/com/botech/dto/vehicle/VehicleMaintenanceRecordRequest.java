package co.com.botech.dto.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Completed vehicle maintenance")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMaintenanceRecordRequest {

    @Schema(description = "Allowed: PREVENTIVO, CORRECTIVO, EMERGENCIA", example = "PREVENTIVO")
    @NotBlank
    private String maintenanceType;

    @Schema(description = "Maintenance title", example = "Cambio de aceite")
    @NotBlank
    private String title;

    @Schema(description = "Maintenance details", example = "Cambio de aceite y filtro de aire")
    private String description;
    @Schema(description = "Person responsible for maintenance", example = "Taller Autos del Norte")
    private String responsibleName;
    @Schema(description = "Responsible person's contact", example = "3109876543")
    private String responsibleContact;
    @Schema(description = "Vehicle mileage at service", example = "85400")
    private Integer mileageAtService;
    @Schema(description = "Maintenance cost", example = "450000")
    private BigDecimal cost;

    @Schema(description = "Maintenance date (yyyy-MM-dd)", example = "2026-10-15")
    @NotNull
    private LocalDate maintenanceDate;

    @Schema(description = "Notes", example = "Cambio de aceite y filtros")
    private String notes;
}