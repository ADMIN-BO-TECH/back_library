package co.com.botech.repository;

import co.com.botech.customDto.VehicleCompleteResponse;
import co.com.botech.customDto.VehicleFleetAndRegisterResponse;
import co.com.botech.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByRfidRegister_Id(Long rfidRegisterId);

    @Query(value = """
                SELECT
                    v.vehicle_id        AS id,
                    v.plate_number      AS plateNumber,
                    v.fleet_number      AS fleetNumber,
                    v.rfid_tag AS rfidTag,
                    v.rfid_register_id AS rfidRegisterId,

                    vd.body_type        AS bodyType,
                    vd.engine_displacement AS engineDisplacement,
                    vd.vehicle_class    AS vehicleClass,
                    vd.color            AS color,
                    vd.fuel_type        AS fuelType,
                    vd.line             AS line,
                    vd.brand            AS brand,
                    vd.model_year       AS modelYear,
                    vd.chassis_number   AS chassisNumber,
                    vd.transit_license_number AS transitLicenseNumber,
                    vd.engine_number    AS engineNumber,
                    vd.serial_number    AS serialNumber,
                    vd.ownership        AS ownership,
                    vd.mechanical_inspection_number AS mechanicalInspectionNumber,
                    vd.transit_department AS transitDepartment,
                    vd.soat_expiration_date AS soatExpirationDate,
                    vd.soat_policy_number AS soatPolicyNumber,
                    vd.service_type     AS serviceType,
                    vd.operation_card_expiration AS operationCardExpiration,
                    vd.operation_card_modality AS operationCardModality,
                    vd.operation_card_number AS operationCardNumber
                FROM vehicles v
                LEFT JOIN vehicles_details vd
                       ON v.vehicle_id = vd.vehicle_id
            """, nativeQuery = true)
    List<VehicleCompleteResponse> findAllVehiclesComplete();

    @Query(value = """
                SELECT
                    v.vehicle_id        AS id,
                    v.plate_number      AS plateNumber,
                    v.fleet_number      AS fleetNumber,
                    v.rfid_tag AS rfidTag,
                    v.rfid_register_id AS rfidRegisterId,

                    vd.body_type        AS bodyType,
                    vd.engine_displacement AS engineDisplacement,
                    vd.vehicle_class    AS vehicleClass,
                    vd.color            AS color,
                    vd.fuel_type        AS fuelType,
                    vd.line             AS line,
                    vd.brand            AS brand,
                    vd.model_year       AS modelYear,
                    vd.chassis_number   AS chassisNumber,
                    vd.transit_license_number AS transitLicenseNumber,
                    vd.engine_number    AS engineNumber,
                    vd.serial_number    AS serialNumber,
                    vd.ownership        AS ownership,
                    vd.mechanical_inspection_number AS mechanicalInspectionNumber,
                    vd.transit_department AS transitDepartment,
                    vd.soat_expiration_date AS soatExpirationDate,
                    vd.soat_policy_number AS soatPolicyNumber,
                    vd.service_type     AS serviceType,
                    vd.operation_card_expiration AS operationCardExpiration,
                    vd.operation_card_modality AS operationCardModality,
                    vd.operation_card_number AS operationCardNumber
                FROM vehicles v
                LEFT JOIN vehicles_details vd
                       ON v.vehicle_id = vd.vehicle_id
                WHERE v.vehicle_id = :vehicleId
            """, nativeQuery = true)
    Optional<VehicleCompleteResponse> findVehiclesComplete(@Param("vehicleId") Long vehicleId);

    @Query(value = """
                SELECT
                    v.vehicle_id        AS id,
                    v.plate_number      AS plateNumber,
                    v.fleet_number      AS fleetNumber,
                    v.rfid_tag AS rfidTag,
                    v.rfid_register_id AS rfidRegisterId,

                    vd.body_type        AS bodyType,
                    vd.engine_displacement AS engineDisplacement,
                    vd.vehicle_class    AS vehicleClass,
                    vd.color            AS color,
                    vd.fuel_type        AS fuelType,
                    vd.line             AS line,
                    vd.brand            AS brand,
                    vd.model_year       AS modelYear,
                    vd.chassis_number   AS chassisNumber,
                    vd.transit_license_number AS transitLicenseNumber,
                    vd.engine_number    AS engineNumber,
                    vd.serial_number    AS serialNumber,
                    vd.ownership        AS ownership,
                    vd.mechanical_inspection_number AS mechanicalInspectionNumber,
                    vd.transit_department AS transitDepartment,
                    vd.soat_expiration_date AS soatExpirationDate,
                    vd.soat_policy_number AS soatPolicyNumber,
                    vd.service_type     AS serviceType,
                    vd.operation_card_expiration AS operationCardExpiration,
                    vd.operation_card_modality AS operationCardModality,
                    vd.operation_card_number AS operationCardNumber
                FROM vehicles v
                LEFT JOIN vehicles_details vd
                       ON v.vehicle_id = vd.vehicle_id
                WHERE v.fleet_number = :fleetNumber
            """, nativeQuery = true)
    Optional<VehicleCompleteResponse> findVehiclesCompleteByFleetNumber(@Param("fleetNumber") String fleetNumber);

    @Query("""
                SELECT  v.fleetNumber        AS fleetNumber,
                        r.id                  AS rfidRegisterId,
                        r.rfidTag             AS rfidTag
                FROM Vehicle v
                LEFT JOIN v.rfidRegister r
                WHERE v.fleetNumber IN :fleetNumbers
            """)
    List<VehicleFleetAndRegisterResponse> findRfidRegistersByFleetNumbers(List<String> fleetNumbers);
}