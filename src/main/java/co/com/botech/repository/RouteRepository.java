package co.com.botech.repository;

import co.com.botech.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("""
             SELECT r
             FROM Route r
             WHERE r.routeDays LIKE CONCAT('%', :searchedDay, '%')
             AND r.status = TRUE
            """)
    List<Route> findActiveRoutesByDay(@Param("searchedDay") String searchedDay);

    @Query("""
            SELECT DISTINCT si.stop.route
            FROM StopInformation si
            WHERE si.student.id = :idRecordStudent
            """)
    List<Route> findRoutesByStudent(@Param("idRecordStudent") Long idRecordStudent);

    @Query(
            """
                    SELECT r
                    FROM Route AS r
                    WHERE r.vehicle.id = :idVehicle
                    AND r.status = true
                    AND LOCATE(:weekDay, REPLACE(LOWER(r.routeDays),' ','' )) > 0
                    AND FUNCTION('TIME', FUNCTION('STR_TO_DATE', r.startTime, '%l:%i %p'))
                                <= FUNCTION('TIME', FUNCTION('STR_TO_DATE', :hour, '%l:%i %p'))
                          AND FUNCTION('TIME', FUNCTION('STR_TO_DATE', r.endTime, '%l:%i %p'))
                                >= FUNCTION('TIME', FUNCTION('STR_TO_DATE', :hour, '%l:%i %p'))
                    """
    )
    List<Route> findActiveRouteByVehicleWithDayAndHour(@Param("idVehicle") Long idVehicle,
                                                       @Param("weekDay") String weekDay,
                                                       @Param("hour") String hour);
}