package co.com.botech.repository;

import co.com.botech.entity.AttendanceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceTypeRepository extends JpaRepository<AttendanceType, Long> {
    Optional<AttendanceType> findByDescription(String description);
}