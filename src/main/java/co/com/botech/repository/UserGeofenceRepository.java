package co.com.botech.repository;

import co.com.botech.customDto.GeofenceForBusProjection;
import co.com.botech.entity.UserGeofence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserGeofenceRepository extends JpaRepository<UserGeofence, Long> {
    List<UserGeofence> findByUserId(Long userId);
    long countByUserId(Long userId);

    @Query("""
        SELECT DISTINCT ug.id AS geofenceId,
               ug.user.id AS userId,
               ug.user.fcmToken AS fcmToken,
               ug.latitude AS latitude,
               ug.longitude AS longitude,
               ug.radius AS radius
        FROM StartedRouteRegister srr
        JOIN srr.vehicle v
        JOIN v.rfidRegister rr
        JOIN srr.route rt
        JOIN Stop s ON s.route.id = rt.id
        JOIN StopInformation si ON si.stop.id = s.id
        JOIN UserFamily uf ON uf.family.id = si.student.family.id
        JOIN UserGeofence ug ON ug.user.id = uf.user.id
        WHERE rr.rfidTag = :rfidTag
          AND srr.status = true
          AND srr.registerDate = :today
          AND FUNCTION('TIME', FUNCTION('STR_TO_DATE', srr.startTime, '%l:%i %p'))
                <= FUNCTION('TIME', FUNCTION('STR_TO_DATE', :hour, '%l:%i %p'))
          AND FUNCTION('TIME', FUNCTION('STR_TO_DATE', srr.endTime, '%l:%i %p'))
                >= FUNCTION('TIME', FUNCTION('STR_TO_DATE', :hour, '%l:%i %p'))
          AND s.status = true
          AND si.student IS NOT NULL
        """)
    List<GeofenceForBusProjection> findGeofencesForBusRfidTag(
            @Param("rfidTag") String rfidTag,
            @Param("today") LocalDate today,
            @Param("hour") String hour);
}
