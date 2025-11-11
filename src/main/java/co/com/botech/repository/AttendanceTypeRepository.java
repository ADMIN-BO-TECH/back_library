package co.com.botech.repository;

import co.com.botech.entity.AttendanceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceTypeRepository extends JpaRepository<AttendanceType, Long> {}