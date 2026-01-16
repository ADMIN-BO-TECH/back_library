package co.com.botech.repository;

import co.com.botech.entity.StopInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StopInformationRepository extends JpaRepository<StopInformation, Long> {
    @Query("""
                SELECT si
                FROM StopInformation si
                JOIN si.stop s
                JOIN s.route r
                WHERE si.student.id = :idStudent
                  AND FUNCTION('TIME', FUNCTION('STR_TO_DATE', r.startTime, '%l:%i %p'))
                        <= FUNCTION('TIME', FUNCTION('STR_TO_DATE', :hour, '%l:%i %p'))
                  AND FUNCTION('TIME', FUNCTION('STR_TO_DATE', r.endTime, '%l:%i %p'))
                        >= FUNCTION('TIME', FUNCTION('STR_TO_DATE', :hour, '%l:%i %p'))
                  AND si.state = :state
                ORDER BY s.stopOrder asc

            """)
    List<StopInformation> findActiveStopsOfStudentInSchedule(
            @Param("idStudent") Long idStudent,
            @Param("hour") String hour,
            @Param("state") String state);


    @Query("""
                SELECT si
                FROM StopInformation si
                JOIN si.stop s
                WHERE si.student.id = :idStudent
                  AND s.route.id = :idRoute
                  AND si.state = :state
                ORDER BY s.stopOrder asc
            """)
    List<StopInformation> findActiveStopsByStudentInAndRoute(
            @Param("idStudent") Long idStudent,
            @Param("idRoute") Long idRoute,
            @Param("state") String state);

    @Modifying(clearAutomatically = true)
    @Query("""
                UPDATE StopInformation AS si
                SET si.state = :finalStatus
                WHERE si.state = :searchedStatus
            """)
    int updateByStatus(@Param("searchedStatus") String searchedStatus,
                       @Param("finalStatus") String finalStatus);

    @Modifying(clearAutomatically = true)
    @Query("""
             DELETE FROM StopInformation si
             WHERE si.state = :searchedStatus
            """)
    int deleteByStatus(@Param("searchedStatus") String searchedStatus);

    @Query("""
                SELECT si
                FROM StopInformation si
                JOIN si.stop s
                WHERE s.route.id = :idRoute
                AND si.state = :status
                ORDER BY s.stopOrder asc
            """)
    List<StopInformation> findStopInformationByStatusAndRoute(
            @Param("idRoute") Long idRoute,
            @Param("status") String status
    );
}