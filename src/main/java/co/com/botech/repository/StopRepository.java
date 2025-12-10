package co.com.botech.repository;

import co.com.botech.entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StopRepository extends JpaRepository<Stop, Long> {
    /**
     * Paradas activas por ruta, ordenadas
     */
    @Query("""
            select s from Stop s
            where s.route.id = :routeId
              and s.status = true
            order by s.stopOrder asc
            """)
    List<Stop> findByRouteIdActiveOrderByOrder(@Param("routeId") Long routeId);

    /**
     * Todas las paradas por ruta (sin filtro)
     */
    List<Stop> findByRoute_Id(Long routeId);

    /**
     * Borrar por tipo de parada
     */
    int deleteByStopType_Id(Long stopTypeId);

    @Query("""
                SELECT DISTINCT si.stop
                FROM StopInformation si
                WHERE si.student.id = :idRecordStudent
            """)
    List<Stop> findStopsByStudent(@Param("idRecordStudent") Long idRecordStudent);
}