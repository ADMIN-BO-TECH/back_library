package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.KindDevice;
import co.com.botech.entity.Route;
import co.com.botech.repository.KindDeviceRepository;
import co.com.botech.repository.RouteRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RouteUtils {
    private final RouteRepository routeRepository;

    public Route getRoute(Long routeId) {
        return routeRepository.findById(routeId)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha ruta con id: " + routeId));
    }
}
