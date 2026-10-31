package co.com.botech.dto.preoperation;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicles ranking")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class VehiclesRankingResponse {
    @Schema(description = "Vehicles")
    private List<VehicleRankingItemDTO> vehicles;
}
