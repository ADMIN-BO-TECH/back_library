package co.com.botech.repository;

import co.com.botech.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByRfidRegister_Id(Long rfidRegisterId);
}