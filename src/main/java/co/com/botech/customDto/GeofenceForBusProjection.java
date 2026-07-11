package co.com.botech.customDto;

public interface GeofenceForBusProjection {
    Long getGeofenceId();
    Long getUserId();
    String getFcmToken();
    Double getLatitude();
    Double getLongitude();
    Double getRadius();
}
