package co.com.botech.repository;

import co.com.botech.constants.AnnouncementStatus;
import co.com.botech.entity.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {


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


    @Query("""
                SELECT a FROM Announcement a
                WHERE a.school.id = :schoolId
                AND (:status IS NULL OR a.status = :status)
                AND a.publishDate <= CURRENT_TIMESTAMP
                AND (:tag IS NULL OR LOWER(a.tags) LIKE LOWER(CONCAT('%', :tag, '%')))
            """)
    Page<Announcement> findPublishedAnnouncementsWithFilters(
            Long schoolId,
            AnnouncementStatus status,
            String tag,
            Pageable pageable
    );
}