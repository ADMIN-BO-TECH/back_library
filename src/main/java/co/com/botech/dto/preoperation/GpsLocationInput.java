package co.com.botech.dto.preoperation;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class GpsLocationInput {
    private Double lat;
    private Double lng;
}
