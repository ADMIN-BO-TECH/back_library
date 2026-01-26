package co.com.botech.repository;

import co.com.botech.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {

    List<EmployeeAttendance> findByEmployeeIdAndAttendanceTimeBetween(Long employee_id, LocalDateTime attendanceTimeInit, LocalDateTime attendanceTimeEnd);
}