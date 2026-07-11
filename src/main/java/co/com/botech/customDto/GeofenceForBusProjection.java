package co.com.botech.customDto;

/**
 * Proyección plana usada por UserGeofenceRepository.findGeofencesForBusRfidTag.
 * Trae en una sola consulta todo lo necesario para construir un BuildedGeofence
 * en el servicio UserGeofence, sin traer entidades completas ni hacer múltiples
 * round-trips (rfidTag -> Vehicle -> Route -> Stop -> Student -> Family -> User -> UserGeofence).
 */
public interface GeofenceForBusProjection {
    Long getGeofenceId();
    String getName();
    Long getUserId();
    String getFcmToken();
    Double getLatitude();
    Double getLongitude();
    Double getRadius();
}
