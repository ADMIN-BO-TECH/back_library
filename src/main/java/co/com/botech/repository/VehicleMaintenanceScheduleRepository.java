package co.com.botech.repository;

import co.com.botech.entity.VehicleMaintenanceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VehicleMaintenanceScheduleRepository extends JpaRepository<VehicleMaintenanceSchedule, Long> {

    List<VehicleMaintenanceSchedule> findByScheduledDateBetweenOrderByScheduledDateAsc(LocalDate startDate, LocalDate endDate);

    List<VehicleMaintenanceSchedule> findByVehicleIdAndScheduledDateBetweenOrderByScheduledDateAsc(Long vehicleId, LocalDate startDate, LocalDate endDate);

    Optional<VehicleMaintenanceSchedule> findFirstByVehicleIdAndStatusAndScheduledDateGreaterThanEqualOrderByScheduledDateAsc(Long vehicleId, String status, LocalDate date);
}