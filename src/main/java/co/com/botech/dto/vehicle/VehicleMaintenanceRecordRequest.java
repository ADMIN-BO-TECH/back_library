package co.com.botech.dto.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMaintenanceRecordRequest {

    @NotBlank
    private String maintenanceType;

    @NotBlank
    private String title;

    private String description;
    private String responsibleName;
    private String responsibleContact;
    private Integer mileageAtService;
    private BigDecimal cost;

    @NotNull
    private LocalDate maintenanceDate;

    private String notes;
}