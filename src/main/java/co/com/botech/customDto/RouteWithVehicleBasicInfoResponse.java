package co.com.botech.customDto;

public interface RouteWithVehicleBasicInfoResponse {
    Long getRouteId();
    String getRouteName();
    Boolean getRouteStatus();
    String getVehicleFleetNumber();
    String getVehiclePlateNumber();

}