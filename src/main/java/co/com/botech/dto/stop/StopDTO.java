package co.com.botech.dto.stop;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.annotation.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Route stop")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StopDTO {
    @Schema(description = "Stop ID; empty when creating", example = "301")
    @Nullable
    private Long id;
    @Schema(description = "Latitude", example = "4.7110")
    private double latitude;
    @Schema(description = "Longitude", example = "-74.0721")
    private double longitude;
    @Schema(description = "Address", example = "Calle 106 # 18-40, Bogotá")
    private String address;
    @Schema(description = "Estimated stop time (hh:mm AM/PM)", example = "06:25 AM")
    private String time;
    @Schema(description = "Stop order in the route", example = "3")
    private int stopOrder;
    @Schema(description = "Route ID", example = "12")
    private Long routeId;
}
