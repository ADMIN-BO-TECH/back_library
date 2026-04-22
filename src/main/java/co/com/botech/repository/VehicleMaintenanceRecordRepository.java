package co.com.botech.repository;

import co.com.botech.entity.VehicleMaintenanceRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface VehicleMaintenanceRecordRepository extends JpaRepository<VehicleMaintenanceRecord, Long> {

    Page<VehicleMaintenanceRecord> findByVehicleIdOrderByMaintenanceDateDesc(Long vehicleId, Pageable pageable);

    Page<VehicleMaintenanceRecord> findByVehicleIdAndMaintenanceTypeOrderByMaintenanceDateDesc(Long vehicleId, String maintenanceType, Pageable pageable);

    Page<VehicleMaintenanceRecord> findByVehicleIdAndMaintenanceDateBetweenOrderByMaintenanceDateDesc(Long vehicleId, LocalDate startDate, LocalDate endDate, Pageable
            pageable);

    Page<VehicleMaintenanceRecord> findByVehicleIdAndMaintenanceTypeAndMaintenanceDateBetweenOrderByMaintenanceDateDesc(Long vehicleId, String maintenanceType, LocalDate
            startDate, LocalDate endDate, Pageable pageable);
}