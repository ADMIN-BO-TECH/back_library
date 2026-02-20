package co.com.botech.dto.stop;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StopDTO {
    private Long id;
    private double latitude;
    private double longitude;
    private String address;
    private boolean status;
    private String time;
    private int stopOrder;
    private Long routeId;
    private String stopType;
}
