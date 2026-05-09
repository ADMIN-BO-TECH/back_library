package co.com.botech.repository;

import co.com.botech.entity.NotificationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NotificationCategoryRepository extends JpaRepository<NotificationCategory, Long> {

    Optional<NotificationCategory> findByCategoryName(String categoryName);

    @Query("""
            SELECT CASE
                WHEN (nc.globalEnabled = true AND NOT EXISTS (
                    SELECT udc FROM UserDisabledCategory udc
                    WHERE udc.user.id = :userId
                    AND udc.notificationCategory.id = :categoryId
                )) THEN true ELSE false END
            FROM NotificationCategory nc
            WHERE nc.id = :categoryId
            """)
    boolean isCategoryEnabledForUser(@Param("userId") Long userId, @Param("categoryId") Long categoryId);
}