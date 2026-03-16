package co.com.botech.repository;

import co.com.botech.entity.LinkManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LinkManagementRepository extends JpaRepository<LinkManagement, Long> {
    List<LinkManagement> findByStatus(boolean status);
    List<LinkManagement> findByVisibleInAppTrueAndStatusTrueOrderByUpdatedAtDesc();
    boolean existsByRoute_Id(Long routeId);
    Optional<LinkManagement> findByRoute_Id(Long routeId);

    @Modifying(clearAutomatically = true)
    @Query("""
        UPDATE LinkManagement lm
        SET lm.status = :finalStatus
        WHERE lm.status = :searchedStatus
    """)
    int updateByStatus(@Param("searchedStatus") Boolean searchedStatus,
                       @Param("finalStatus") Boolean finalStatus);

    @Modifying(clearAutomatically = true)
    @Query("""
        UPDATE LinkManagement lm
        SET lm.status = :finalStatus,
            lm.updatedAt = :updatedAt
        WHERE lm.id = :linkId
    """)
    int updateStatusById(@Param("linkId") Long linkId,
                         @Param("finalStatus") Boolean finalStatus,
                         @Param("updatedAt") LocalDateTime updatedAt);

    @Modifying(clearAutomatically = true)
    @Query("""
        UPDATE LinkManagement lm
        SET lm.visibleInApp = :visibleInApp,
            lm.updatedAt = :updatedAt
        WHERE lm.id = :linkId
    """)
    int updateVisibleInAppById(@Param("linkId") Long linkId,
                               @Param("visibleInApp") Boolean visibleInApp,
                               @Param("updatedAt") LocalDateTime updatedAt);

    List<LinkManagement> findByRoute_School_Name(String schoolName);
}