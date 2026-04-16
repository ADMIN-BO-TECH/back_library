package co.com.botech.dto.stop;

import lombok.*;

import javax.annotation.Nullable;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StopGroupedDTO {
    @Nullable
    private Long id;
    private double latitude;
    private double longitude;
    private String address;
    private String time;
    private int stopOrder;
    private String route;
    private Long routeId;
}
