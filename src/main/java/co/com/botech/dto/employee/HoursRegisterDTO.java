package co.com.botech.dto.employee;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Hours register")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoursRegisterDTO {
    @Schema(description = "Day hours")
    private double dayHours;
    @Schema(description = "Night hours")
    private double nightHours;
    @Schema(description = "Total hours")
    private double totalHours;
}
