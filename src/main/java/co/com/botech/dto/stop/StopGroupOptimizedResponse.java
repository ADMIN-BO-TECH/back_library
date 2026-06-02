package co.com.botech.dto.stop;

import lombok.*;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StopGroupOptimizedResponse {
    private int groupId;
    private double latitude;
    private double longitude;
    private String address;
    private List<AllStopsCrudDTO> stops;
}
