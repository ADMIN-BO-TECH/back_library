package co.com.botech.repository;

import co.com.botech.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {


    @Query(
            value = "SELECT * FROM attendance a WHERE ${where}",
            nativeQuery = true)
    List<Attendance> getAttendanceByCustomQuery(@Param("where") String whereClause);

    @Query(
            value = "SELECT COUNT(*) FROM attendance a WHERE ${where}",
            nativeQuery = true
    )
    Integer countAttendanceByCustomQuery(@Param("where") String whereClause);
}
