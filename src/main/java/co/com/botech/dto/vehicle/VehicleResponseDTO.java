package co.com.botech.dto.vehicle;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {

    private Long id;
    private String plateNumber;
    private String fleetNumber;
    private Long rfidRegisterId;
    private String rfidTag;
    private String bodyType;
    private String engineDisplacement;
    private String vehicleClass;
    private String color;
    private String fuelType;
    private String line;
    private String brand;
    private String modelYear;
    private String chassisNumber;
    private String transitLicenseNumber;
    private String engineNumber;
    private String serialNumber;
    private String ownership;
    private String mechanicalInspectionNumber;
    private String transitDepartment;
    private String soatExpirationDate;
    private String soatPolicyNumber;
    private String serviceType;
    private String operationCardExpiration;
    private String operationCardModality;
    private String operationCardNumber;
}
