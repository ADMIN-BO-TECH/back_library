package co.com.botech.dto.stop;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Group nearby route stops")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StopOptimizerRequest {
    @Schema(description = "Route ID", example = "12")
    @NotNull
    private Long routeId;
    @Schema(description = "Radius in meters for grouping stops", example = "200")
    @Positive
    private double radiusInMeters;
}