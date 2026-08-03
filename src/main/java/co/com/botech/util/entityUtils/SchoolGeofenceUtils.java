package co.com.botech.util.entityUtils;

import co.com.botech.entity.SchoolGeofences;
import co.com.botech.repository.SchoolGeofencesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class SchoolGeofenceUtils {

    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory(new PrecisionModel(), 4326);
    private static final double ERROR_EPSILON = 1e-7;

    private final SchoolGeofencesRepository schoolGeofencesRepository;
    private final ConcurrentHashMap<Long, Polygon> geofenceCache = new ConcurrentHashMap<>();

    public boolean validateGeocerca(double lat, double lon, String schoolName, Long schoolId) {
        if (schoolId == null) {
            return false;
        }
        boolean cacheHit = geofenceCache.containsKey(schoolId);
        Polygon polygon = geofenceCache.computeIfAbsent(schoolId, id -> loadPolygon(schoolName, id));
        log.info("[Geocerca] schoolId={} | {} | cacheSize={}", schoolId,
                cacheHit ? "CACHE HIT" : "CACHE MISS → cargando de BD", geofenceCache.size());
        Point point = GEOMETRY_FACTORY.createPoint(new Coordinate(lat, lon));
        return polygon.contains(point) || polygon.distance(point) <= ERROR_EPSILON;
    }

    public void evictGeofence(Long schoolId) {
        geofenceCache.remove(schoolId);
    }

    private Polygon loadPolygon(String schoolName, Long schoolId) {
        Optional<SchoolGeofences> opt = schoolGeofencesRepository.findBySchool_Id(schoolId);
        if (opt.isEmpty()) {
            throw new RuntimeException("No se ha encontrado geocerca del cliente " + schoolName);
        }
        SchoolGeofences g = opt.get();
        Coordinate[] coords = {
            new Coordinate(g.getLatitude1(), g.getLongitude1()),
            new Coordinate(g.getLatitude2(), g.getLongitude2()),
            new Coordinate(g.getLatitude3(), g.getLongitude3()),
            new Coordinate(g.getLatitude4(), g.getLongitude4()),
            new Coordinate(g.getLatitude1(), g.getLongitude1())
        };
        return GEOMETRY_FACTORY.createPolygon(coords);
    }
}