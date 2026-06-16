package co.com.botech.repository;

import co.com.botech.constants.WorkBlockSource;
import co.com.botech.constants.WorkBlockStatus;
import co.com.botech.entity.WorkBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface WorkBlockRepository extends JpaRepository<WorkBlock, Long> {

    List<WorkBlock> findByEmployeeIdAndWorkDateBetweenAndStatusOrderByWorkDateAscStartTimeAsc(
            Long employeeId, LocalDate start, LocalDate end, WorkBlockStatus status);

    List<WorkBlock> findByEmployeeIdAndWorkDateAndSourceAndStatusAndIsCompleteOrderByStartTimeAsc(
            Long employeeId, LocalDate workDate, WorkBlockSource source, WorkBlockStatus status, Boolean isComplete);

    @Query("SELECT MAX(wb.sourceStartAttendanceId) FROM WorkBlock wb " +
            "WHERE wb.employee.id = :empId AND wb.source = :source AND wb.sourceStartAttendanceId IS NOT NULL")
    Long findMaxConsumedStartAttendanceId(@Param("empId") Long employeeId, @Param("source") WorkBlockSource source);

    @Query("SELECT MAX(wb.sourceEndAttendanceId) FROM WorkBlock wb " +
            "WHERE wb.employee.id = :empId AND wb.source = :source AND wb.sourceEndAttendanceId IS NOT NULL")
    Long findMaxConsumedEndAttendanceId(@Param("empId") Long employeeId, @Param("source") WorkBlockSource source);
}
