package co.com.botech.repository;

import co.com.botech.customDto.UserNotificationPreference;
import co.com.botech.entity.UserDisabledNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDisabledNotificationRepository extends JpaRepository<UserDisabledNotification, Long> {

    @Query(value = """
            SELECT
                nc.category_name AS categoryName,
                CASE WHEN EXISTS (
                    SELECT 1 FROM user_disabled_notification udn
                    WHERE udn.notification_category_id = nc.notification_category_id
                    AND udn.user_id = :userId
                ) THEN 0 ELSE 1 END AS enabled
            FROM notification_category nc
            WHERE nc.global_enabled = true OR nc.global_enabled IS NULL
            """, nativeQuery = true)
    List<UserNotificationPreference> findUserNotificationPreferences(@Param("userId") Long userId);

    boolean existsByUserIdAndNotificationCategoryId(Long userId, Long notificationCategoryId);

    void deleteByUserIdAndNotificationCategoryId(Long userId, Long notificationCategoryId);

    @Modifying
    @Query("DELETE FROM UserDisabledNotification udn WHERE udn.user.id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);
}