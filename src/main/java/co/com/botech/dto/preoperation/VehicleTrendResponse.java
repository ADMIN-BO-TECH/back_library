package co.com.botech.dto.preoperation;

import lombok.*;

import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class VehicleTrendResponse {
    private String granularity;
    private List<TrendPointDTO> points;
}
