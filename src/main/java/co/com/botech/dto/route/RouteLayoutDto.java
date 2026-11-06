package co.com.botech.dto.route;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Route layout from Google Maps")
@Getter
@Setter
@ToString
public class RouteLayoutDto {

    @Schema(description = "Route layout points")
    @NotEmpty(message = "coordinates is required")
    @Valid
    private List<CoordinateDto> coordinates;

    @Schema(description = "Route ID", example = "12")
    @NotBlank(message = "routeId is required")
    @Pattern(regexp = "^\\d{0,9}$", message = "routeId format invalid")
    private String routeId;

    @Schema(description = "Encoded polyline for the full route", example = "a~l~Fjk~uOwHJy@P")
    private String totalEncryptedPolyline;

    @Schema(description = "Total route distance", example = "18.4 km")
    private String totalDistance;

    @Schema(description = "Start address", example = "Calle 100 # 15-20, Bogotá")
    private String startAddress;

    @Schema(description = "End address", example = "Carrera 7 # 120-30, Bogotá")
    private String endAddress;

    @Schema(description = "Total estimated time", example = "45 min")
    private String totalEstimatedTime;

    @Schema(description = "Route layout point with segment data")
    @Getter
    @Setter
    @ToString
    public static class CoordinateDto {

        @Schema(description = "Point order in the route", example = "1")
        @NotBlank
        @Pattern(regexp = "^\\d{0,3}$")
        private String order;

        @Schema(description = "Point latitude", example = "4.6950")
        @NotBlank
        @Pattern(regexp = "^([+-]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?))$")
        private String latitude;

        @Schema(description = "Point longitude", example = "-74.0310")
        @NotBlank
        @Pattern(regexp = "^([+-]?((1[0-7]\\d(\\.\\d+)?|\\d{1,2}(\\.\\d+)?|180(\\.0+)?)))$")
        private String longitude;

        @Schema(description = "Distance to next point", example = "1.2 km")
        private String waypointSegmentDistance;

        @Schema(description = "Segment start address", example = "Calle 100 # 15-20, Bogotá")
        private String waypointSegmentStartAddress;

        @Schema(description = "Segment end address", example = "Calle 106 # 18-40, Bogotá")
        private String waypointSegmentEndAddress;

        @Schema(description = "Segment estimated time", example = "4 min")
        private String waypointSegmentEstimatedTime;
    }
}
