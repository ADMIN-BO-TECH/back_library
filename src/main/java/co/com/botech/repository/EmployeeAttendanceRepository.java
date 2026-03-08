package co.com.botech.repository;

import co.com.botech.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {

    List<EmployeeAttendance> findByEmployeeIdAndAttendanceTimeBetween(Long employee_id, LocalDateTime attendanceTimeInit, LocalDateTime attendanceTimeEnd);

    @Query("""
        select ea
        from EmployeeAttendance ea
        join fetch ea.employee e
        where e.id in :employeeIds
          and ea.attendanceTime between :start and :end
        order by e.id asc, ea.attendanceTime asc
    """)
    List<EmployeeAttendance> findForWorkedHoursExcel(
            @Param("employeeIds") List<Long> employeeIds,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}