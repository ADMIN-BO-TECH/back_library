package co.com.botech.dto.workBlock;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Coordinates")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LocationDTO {
    @Schema(description = "Latitude", example = "4.7110")
    private Double lat;
    @Schema(description = "Longitude", example = "-74.0721")
    private Double lng;
}
