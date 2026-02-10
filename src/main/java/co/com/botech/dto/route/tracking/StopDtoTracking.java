package co.com.botech.dto.route.tracking;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StopDtoTracking {
    private String id;
    private Integer order;
    private String type;
    private String time;
    private String address;
    private LocationDto location;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LocationDto {
        private Double lat;
        private Double lng;
    }
}
