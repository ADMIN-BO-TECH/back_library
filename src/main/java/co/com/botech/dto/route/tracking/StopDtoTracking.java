package co.com.botech.dto.route.tracking;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StopDtoTracking {
    private Long id;
    private Integer order;
    private Long type;
    private String time;
    private String address;
    private LocationDto location;
    private String status;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LocationDto {
        private Double lat;
        private Double lng;
    }
}