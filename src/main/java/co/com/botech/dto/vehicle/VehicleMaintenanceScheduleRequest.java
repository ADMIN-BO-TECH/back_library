package co.com.botech.dto.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Scheduled vehicle maintenance")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMaintenanceScheduleRequest {

    @Schema(description = "Scheduled date (yyyy-MM-dd)", example = "2026-11-15")
    @NotNull
    private LocalDate scheduledDate;

    @Schema(description = "Scheduled maintenance title", example = "Revisión de frenos")
    @NotBlank
    private String title;

    @Schema(description = "Scheduled maintenance details", example = "Revisión de pastillas y líquido de frenos")
    private String description;
}
