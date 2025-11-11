package co.com.botech.repository;

import co.com.botech.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {}