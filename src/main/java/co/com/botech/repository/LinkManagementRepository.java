package co.com.botech.repository;

import co.com.botech.entity.LinkManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface LinkManagementRepository extends JpaRepository<LinkManagement, Long> {
    List<LinkManagement> findByStatus(boolean status);

    boolean existsByRoute_Id(Long routeId);

    @Modifying(clearAutomatically = true)
    @Query("""
                UPDATE LinkManagement AS lm
                SET lm.status = :finalStatus
                WHERE lm.status = :searchedStatus
            """)
    int updateByStatus(@Param("searchedStatus") Boolean searchedStatus,
                       @Param("finalStatus") Boolean finalStatus);

    @Modifying(clearAutomatically = true)
    @Query("""
                UPDATE LinkManagement AS lm
                SET lm.status = :finalStatus,
                lm.updatedAt = :updatedAt
                WHERE lm.id = :linkId
            """)
    int updateStatusById(@Param("linkId") Long linkId,
                                    @Param("finalStatus") Boolean finalStatus,
                                    @Param("updatedAt") LocalDateTime updatedAt);

    List<LinkManagement> findByRoute_School_Name(String schoolName);

    @Query(value = """
    SELECT lm.*
    FROM link_management lm
    INNER JOIN route r ON lm.route_id = r.route_id
    WHERE :currentTime BETWEEN
        CAST(STR_TO_DATE(r.start_time, '%h:%i %p') AS TIME)
        AND CAST(STR_TO_DATE(r.end_time, '%h:%i %p') AS TIME)
      AND lm.route_id = :routeId
    """, nativeQuery = true)
    List<LinkManagement> findByRouteIdAndValidTime(
            @Param("routeId") Long routeId,
            @Param("currentTime") LocalTime currentTime
    );



}
