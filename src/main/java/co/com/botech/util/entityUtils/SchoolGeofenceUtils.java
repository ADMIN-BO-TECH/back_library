package co.com.botech.util.entityUtils;

import co.com.botech.entity.SchoolGeofences;
import co.com.botech.repository.SchoolGeofencesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.geom.Path2D;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SchoolGeofenceUtils {

    private final SchoolGeofencesRepository schoolGeofencesRepository;

    public boolean validateGeocerca(double lat, double lon, String schoolName, Long schoolId) {
        if (schoolId == null) {
            return false;
        }
        Path2D clientGeocerca = getGeocerca(schoolName, schoolId);
        double errorEpsilon = 1e-7;
        return clientGeocerca.contains(lat, lon) || clientGeocerca.intersects(lat - errorEpsilon, lon - errorEpsilon, 2 * errorEpsilon, 2 * errorEpsilon);
    }

    private Path2D getGeocerca(String schoolName, Long schoolId) {
        Optional<SchoolGeofences> clientGeocercaOpt = schoolGeofencesRepository.findBySchool_Id(schoolId);
        if (clientGeocercaOpt.isEmpty()) {
            throw new RuntimeException("No se ha encontrado geocerca del cliente " + schoolName);
        }
        SchoolGeofences clientGeocerca = clientGeocercaOpt.get();
        Path2D polygon = new Path2D.Double();

        polygon.moveTo(clientGeocerca.getLatitude1(), clientGeocerca.getLongitude1());
        polygon.lineTo(clientGeocerca.getLatitude2(), clientGeocerca.getLongitude2());
        polygon.lineTo(clientGeocerca.getLatitude3(), clientGeocerca.getLongitude3());
        polygon.lineTo(clientGeocerca.getLatitude4(), clientGeocerca.getLongitude4());
        polygon.lineTo(clientGeocerca.getLatitude1(), clientGeocerca.getLongitude1());
        polygon.closePath();
        return polygon;
    }
}
