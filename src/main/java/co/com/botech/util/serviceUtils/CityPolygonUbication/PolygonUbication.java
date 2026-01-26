package co.com.botech.util.serviceUtils.CityPolygonUbication;
import co.com.botech.util.serviceUtils.CityPolygonUbication.Coordinates.PolygonInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.geom.Path2D;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

@Slf4j
@RequiredArgsConstructor
@Service
public class PolygonUbication {

    private static Map<String, Path2D> polygons;

    static {
        polygons = new HashMap<>();
        ServiceLoader<PolygonInterface> loader = ServiceLoader.load(PolygonInterface.class);
        for (PolygonInterface polygon : loader) {
            polygons.put(polygon.getName(), polygon.getPolygon());
        }
    }


    public static PolygonResponse insidePolygonVerification(double latitud, double longitud) {
        try {
            PolygonResponse response = new PolygonResponse();
            response.setUbication("Externo");
            response.setIsCity(false);
            for (Map.Entry<String, Path2D> polygon : polygons.entrySet()) {
                if (polygon.getValue().contains(latitud, longitud)) {
                    response.setUbication(polygon.getKey());
                    response.setIsCity(true);
                    break;
                }
            }
            return response;
        } catch (Exception e) {
            log.error(String.valueOf(new RuntimeException("Error validar ubicación en Poligono", e)));
            throw new RuntimeException("Error validar ubicación en Poligono");
        }
    }
}
