package co.com.botech.customDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface VehicleCompleteResponse {
    Long getId();
    String getPlateNumber();
    String getFleetNumber();
    String getRfidTag();
    String getBodyType();
    String getEngineDisplacement();
    String getVehicleClass();
    String getColor();
    String getFuelType();
    String getLine();
    String getBrand();
    String getModelYear();
    String getChassisNumber();
    String getTransitLicenseNumber();
    String getEngineNumber();
    String getSerialNumber();
    String getOwnership();
    String getMechanicalInspectionNumber();
    String getTransitDepartment();
    String getSoatPolicyNumber();
    String getServiceType();
    String getOperationCardModality();
    String getOperationCardNumber();
    LocalDate getSoatExpirationDate();
    LocalDate getOperationCardExpiration();
    LocalDate getMechanicalInspectionExpiration();
    Integer getMileage();
    LocalDateTime getMileageLastUpdated();
    Integer getVehicleCapacity();
    String getVehicleStatus();
}