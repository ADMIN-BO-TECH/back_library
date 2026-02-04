package co.com.botech.repository;

import co.com.botech.entity.StopType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StopTypeRepository extends JpaRepository<StopType, Long> {
    Optional<StopType> getStopTypeByStopTypeName(String stopTypeName);
}