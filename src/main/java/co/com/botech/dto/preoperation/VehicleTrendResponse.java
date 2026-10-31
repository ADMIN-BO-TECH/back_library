package co.com.botech.dto.preoperation;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicle trend")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class VehicleTrendResponse {
    @Schema(description = "Granularity")
    private String granularity;
    @Schema(description = "Points")
    private List<TrendPointDTO> points;
}
