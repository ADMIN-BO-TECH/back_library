package co.com.botech.repository;

import co.com.botech.entity.Route;
import co.com.botech.entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("""
        SELECT DISTINCT si.stop.route
        FROM StopInformation si
        WHERE si.student.id = :idRecordStudent
        """)
    List<Route> findRoutsByStudent(@Param("idRecordStudent") Long idRecordStudent);
}