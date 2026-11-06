package co.com.botech.dto.stop;

import lombok.*;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Stop group optimized")
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StopGroupOptimizedResponse {
    @Schema(description = "Group ID")
    private int groupId;
    @Schema(description = "Latitude")
    private double latitude;
    @Schema(description = "Longitude")
    private double longitude;
    @Schema(description = "Address")
    private String address;
    @Schema(description = "Stops")
    private List<AllStopsCrudDTO> stops;
}
