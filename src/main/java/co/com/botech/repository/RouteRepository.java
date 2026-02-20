package co.com.botech.repository;

import co.com.botech.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

    List<Route> findByRouteNameAndStatus(String routeName, Boolean status);

    List<Route> findByRouteNameAndStatusAndIdNot(String routeName, Boolean status, Long id);

    Optional<Route> findByIdAndStatus(Long id, Boolean status);

    @Query("""
        SELECT r
        FROM Route r
        WHERE r.status = TRUE
          AND LOCATE(:day, REPLACE(LOWER(r.routeDays),' ','')) > 0
          AND (
               (:vehicleId != 1 AND r.vehicle.id = :vehicleId)
            OR (:operatorId != 1 AND r.operator.id = :operatorId)
            OR (:assistantId != 1 AND r.assistant.id = :assistantId)
          )
          AND (
                FUNCTION('STR_TO_DATE', :startTime, '%l:%i %p')
                  < FUNCTION('STR_TO_DATE', r.endTime, '%l:%i %p')
            AND FUNCTION('STR_TO_DATE', :endTime, '%l:%i %p')
                  > FUNCTION('STR_TO_DATE', r.startTime, '%l:%i %p')
          )
    """)
    List<Route> findConflictingRoutesForCreate(@Param("vehicleId") Long vehicleId,
                                               @Param("operatorId") Long operatorId,
                                               @Param("assistantId") Long assistantId,
                                               @Param("startTime") String startTime,
                                               @Param("endTime") String endTime,
                                               @Param("day") String day);

    @Query("""
        SELECT r
        FROM Route r
        WHERE r.status = TRUE
          AND r.id <> :routeId
          AND LOCATE(:day, REPLACE(LOWER(r.routeDays),' ','')) > 0
          AND (
               (:vehicleId != 1 AND r.vehicle.id = :vehicleId)
            OR (:operatorId != 1 AND r.operator.id = :operatorId)
            OR (:assistantId != 1 AND r.assistant.id = :assistantId)
          )
          AND (
                FUNCTION('STR_TO_DATE', :startTime, '%l:%i %p')
                  < FUNCTION('STR_TO_DATE', r.endTime, '%l:%i %p')
            AND FUNCTION('STR_TO_DATE', :endTime, '%l:%i %p')
                  > FUNCTION('STR_TO_DATE', r.startTime, '%l:%i %p')
          )
    """)
    List<Route> findConflictingRoutesForUpdate(@Param("vehicleId") Long vehicleId,
                                               @Param("operatorId") Long operatorId,
                                               @Param("assistantId") Long assistantId,
                                               @Param("routeId") Long routeId,
                                               @Param("startTime") String startTime,
                                               @Param("endTime") String endTime,
                                               @Param("day") String day);

    @Query("""
        SELECT r
        FROM Route r
        WHERE r.status = TRUE
          AND r.vehicle.id = :vehicleId
          AND :vehicleId != 1
          AND (
                FUNCTION('STR_TO_DATE', :startTime, '%l:%i %p')
                  < FUNCTION('STR_TO_DATE', r.endTime, '%l:%i %p')
            AND FUNCTION('STR_TO_DATE', :endTime, '%l:%i %p')
                  > FUNCTION('STR_TO_DATE', r.startTime, '%l:%i %p')
          )
          AND LOCATE(LOWER(:day), REPLACE(LOWER(r.routeDays),' ','')) > 0
    """)
    List<Route> findConflictingRoutesByVehicleOnly(@Param("vehicleId") Long vehicleId,
                                                   @Param("startTime") String startTime,
                                                   @Param("endTime") String endTime,
                                                   @Param("day") String day);

    Optional<Route> findByIdAndSchool_Id(Long routeId, Long schoolId);
    List<Route> findBySchool_IdAndStatusTrue(Long schoolId);
}