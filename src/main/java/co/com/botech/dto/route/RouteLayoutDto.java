package co.com.botech.dto.route;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RouteLayoutDto {

    @NotEmpty(message = "coordinates is required")
    @Valid
    private List<CoordinateDto> coordinates;

    @NotBlank(message = "routeId is required")
    @Pattern(regexp = "^\\d{0,9}$", message = "routeId format invalid")
    private String routeId;

    private String totalEncryptedPolyline;

    private String totalDistance;

    private String startAddress;

    private String endAddress;

    private String totalEstimatedTime;

    @Getter
    @Setter
    @ToString
    public static class CoordinateDto {

        @NotBlank
        @Pattern(regexp = "^\\d{0,3}$")
        private String order;

        @NotBlank
        @Pattern(regexp = "^([+-]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?))$")
        private String latitude;

        @NotBlank
        @Pattern(regexp = "^([+-]?((1[0-7]\\d(\\.\\d+)?|\\d{1,2}(\\.\\d+)?|180(\\.0+)?)))$")
        private String longitude;

        private String waypointSegmentDistance;

        private String waypointSegmentStartAddress;

        private String waypointSegmentEndAddress;

        private String waypointSegmentEstimatedTime;
    }
}
