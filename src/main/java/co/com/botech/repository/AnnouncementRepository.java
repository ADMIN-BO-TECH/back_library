package co.com.botech.repository;

import co.com.botech.constants.AnnouncementStatus;
import co.com.botech.entity.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    Page<Announcement> findBySchoolId(Long schoolId, Pageable pageable);

    Page<Announcement> findBySchoolIdAndStatus(
            Long schoolId,
            AnnouncementStatus status,
            Pageable pageable
    );

    @Query("""
                SELECT a FROM Announcement a
                WHERE a.school.id = :schoolId
                AND (:status IS NULL OR a.status = :status)
                AND (:tag IS NULL OR LOWER(a.tags) LIKE LOWER(CONCAT('%', :tag, '%')))
            """)
    Page<Announcement> findWithFilters(
            Long schoolId,
            AnnouncementStatus status,
            String tag,
            Pageable pageable
    );

    List<Announcement> findBySchoolIdOrderByPublishDateDesc(Long schoolId);

    List<Announcement> findBySchoolIdAndStatusOrderByPublishDateDesc(
            Long schoolId,
            AnnouncementStatus status
    );

    @Query("""
                SELECT a FROM Announcement a
                WHERE a.school.id = :schoolId
                AND (:status IS NULL OR a.status = :status)
                AND (:tag IS NULL OR LOWER(a.tags) LIKE LOWER(CONCAT('%', :tag, '%')))
                ORDER BY a.publishDate DESC
            """)
    List<Announcement> findWithFilters(
            Long schoolId,
            AnnouncementStatus status,
            String tag
    );

    @Query("""
                SELECT a FROM Announcement a
                WHERE a.school.id = :schoolId
                AND a.status = co.com.botech.constants.AnnouncementStatus.PUBLISHED
                AND a.publishDate <= CURRENT_TIMESTAMP
                AND (:tag IS NULL OR LOWER(a.tags) LIKE LOWER(CONCAT('%', :tag, '%')))
                ORDER BY a.publishDate DESC
            """)
    List<Announcement> findPublishedForTracking(
            Long schoolId,
            String tag
    );
}