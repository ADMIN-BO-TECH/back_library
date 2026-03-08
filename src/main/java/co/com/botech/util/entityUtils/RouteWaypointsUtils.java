package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.RouteWaypoints;
import co.com.botech.repository.RouteWaypointsRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RouteWaypointsUtils {

    private final RouteWaypointsRepository routeWaypointsRepository;

    public RouteWaypoints getRouteWaypoint(Long routeWaypointId) {
        return routeWaypointsRepository.findById(routeWaypointId)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado punto auxiliar de ruta con id: " + routeWaypointId));
    }

    public void validateRouteWaypointExists(Long routeWaypointId) {
        if (!routeWaypointsRepository.existsById(routeWaypointId)) {
            throw new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                    "No se ha encontrado punto auxiliar de ruta con id: " + routeWaypointId);
        }
    }
}
