package co.com.botech.dto.routeWaypoint;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateRouteWaypointRequest {
    @Valid
    List<UpdateRouteWaypointDTO> routeWaypoints;
}