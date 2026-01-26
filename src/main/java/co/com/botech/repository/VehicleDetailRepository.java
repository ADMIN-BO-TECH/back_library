package co.com.botech.repository;

import co.com.botech.entity.VehicleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleDetailRepository extends JpaRepository<VehicleDetail, Long> {
    Optional<VehicleDetail> findByVehicleId(Long vehicleId);
}