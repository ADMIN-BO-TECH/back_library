package co.com.botech.util.geocoding;

import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.google.maps.GeoApiContext;

@Getter
@Slf4j
@RequiredArgsConstructor
public class GeocodingService {

    private final GeoApiContext geocodingContext;

    public String getAddressByCoordinates(double lat, double lon) {
        try {
            LatLng position = new LatLng(lat, lon);
            GeocodingResult[] geocodingResults = GeocodingApi.reverseGeocode(geocodingContext, position).await();
            if (geocodingResults != null && geocodingResults.length > 0) {
                return geocodingResults[0].formattedAddress;
            } else {
                log.warn("No se encontraron resultados de geocodificación para las coordenadas: {}, {}", lat, lon);
                return "Dirección no encontrada";
            }
        } catch (Exception e) {
            log.error("Error al obtener la dirección para las coordenadas: {}, {}. Error: {}", lat, lon, e.getMessage());
            return "Error al obtener la dirección en coordenadas: " + lat + ", " + lon;
        }
    }
}
