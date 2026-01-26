package co.com.botech.dto.vehicle;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequestDTO {


    @Min(value = 1, message = "El id del vehículo debe ser un número positivo")
    private Long id;

    @NotBlank(message = "La placa es obligatoria")
    @Pattern(
            regexp = "^[A-Z]{3}[0-9]{3}$",
            message = "La placa debe tener el formato AAA000"
    )
    private String plateNumber;

    @NotBlank(message = "El número de flota es obligatorio")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "El número de flota debe contener solo números"
    )
    private String fleetNumber;

    @Min(value = 1, message = "El id del registro RFID debe ser un número positivo")
    private Long rfidRegisterId;


    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "La carrocería no puede contener caracteres especiales"
    )
    private String bodyType;

    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El cilindraje no puede contener caracteres especiales"
    )
    private String engineDisplacement;

    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "La clase del vehículo no puede contener caracteres especiales"
    )
    private String vehicleClass;

    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El color no puede contener caracteres especiales"
    )
    private String color;

    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El tipo de combustible no puede contener caracteres especiales"
    )
    private String fuelType;

    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "La línea no puede contener caracteres especiales"
    )
    private String line;

    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "La marca no puede contener caracteres especiales"
    )
    private String brand;

    @Pattern(
            regexp = "^[0-9]+$",
            message = "El modelo debe contener solo números"
    )
    private String modelYear;

    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El número de chasis no puede contener caracteres especiales"
    )
    private String chassisNumber;

    @Pattern(
            regexp = "^[A-Za-z0-9 .,_-]+$",
            message = "El número de licencia de tránsito no puede contener caracteres especiales"
    )
    private String transitLicenseNumber;

    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El número de motor no puede contener caracteres especiales"
    )
    private String engineNumber;

    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El número de serie no puede contener caracteres especiales"
    )
    private String serialNumber;

    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "La propiedad no puede contener caracteres especiales"
    )
    private String ownership;

    @Pattern(
            regexp = "^[A-Za-z0-9 .,_-]+$",
            message = "El número de revisión técnico-mecánica no puede contener caracteres especiales"
    )
    private String mechanicalInspectionNumber;

    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "La secretaría de tránsito no puede contener caracteres especiales"
    )
    private String transitDepartment;

    @Pattern(
            regexp = "^[A-Za-z0-9 .,_-]+$",
            message = "La fecha de vencimiento del SOAT no puede contener caracteres especiales"
    )
    private String soatExpirationDate;

    @Pattern(
            regexp = "^[A-Za-z0-9 .,_-]+$",
            message = "El número de póliza del SOAT no puede contener caracteres especiales"
    )
    private String soatPolicyNumber;

    @Pattern(
            regexp = "^[A-Za-z0-9 áéíóúÁÉÍÓÚñÑ_().#,/*\\r\\n-:]+$",
            message = "El tipo de servicio no puede contener caracteres especiales"
    )
    private String serviceType;

    @Pattern(
            regexp = "^[A-Za-z0-9 .,_-]+$",
            message = "La fecha de vencimiento de la tarjeta de operación no puede contener caracteres especiales"
    )
    private String operationCardExpiration;

    @Pattern(
            regexp = "^[A-Za-z0-9 ]+$",
            message = "La modalidad de la tarjeta de operación no puede contener caracteres especiales"
    )
    private String operationCardModality;

    @Pattern(
            regexp = "^[A-Za-z0-9 .,_-]+$",
            message = "El número de la tarjeta de operación no puede contener caracteres especiales"
    )
    private String operationCardNumber;
}
