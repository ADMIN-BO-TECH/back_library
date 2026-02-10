package co.com.botech.repository;

import co.com.botech.entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
    List<Stop> findByRoute_Id(Long routeId);
    List<Stop> findByRoute_IdAndStatus(Long routeId, boolean status);

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

    @Query("""
                SELECT s FROM Stop s
                WHERE s.route.id = :idRoute
                AND s.status = TRUE
                ORDER BY s.stopOrder asc
            """)
    List<Stop> findByRouteStateAndOrderedByOrderAsc(@Param("idRoute") Long idRoute);

    @Modifying(clearAutomatically = true)
    @Query("""
                DELETE FROM Stop s
                WHERE s.id  IN :stopIds
            """)
    int deleteByStopIds(@Param("stopIds") List<Long> stopIds);

    @Query("""
                SELECT s FROM Stop s
                WHERE s.route.id = :idRoute
                AND s.route.status = TRUE
                AND s.status = TRUE
                ORDER BY s.stopOrder asc
            """)
    List<Stop> findByStateRouteStateAndOrderedByOrderAsc(@Param("idRoute") Long idRoute);
    List<Stop> findByRoute_IdAndStatusTrueOrderByStopOrderAsc(Long routeId);
}