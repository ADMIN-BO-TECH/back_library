package co.com.botech.dto.route;

import co.com.botech.dto.stop.StopDTO;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class RouteDetailResponseCardinal {
    private List<StopDTO> stops;
    private String totalEncryptedPolyline;

}
