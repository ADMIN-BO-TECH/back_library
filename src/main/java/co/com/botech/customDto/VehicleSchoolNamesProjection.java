package co.com.botech.customDto;

/** Colegios de las rutas activas de un vehiculo, unidos con ", ". */
public interface VehicleSchoolNamesProjection {
    Long getVehicleId();
    String getSchoolNames();
}
