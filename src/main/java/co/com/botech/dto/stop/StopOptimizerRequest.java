package co.com.botech.dto.stop;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StopOptimizerRequest {
    @NotNull
    private Long routeId;
    @Positive
    private double radiusInMeters;
}