package co.com.botech.repository;

import co.com.botech.customDto.UserNotificationPreference;
import co.com.botech.entity.UserDisabledNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDisabledNotificationRepository extends JpaRepository<UserDisabledNotification, Long> {

    @Query(value = """
            SELECT
                nc.category_name  AS categoryName,
                ch.channel_name   AS channelName,
                CASE WHEN udn.user_disabled_notification_id IS NULL THEN true ELSE false END AS enabled
            FROM notification_category nc
            CROSS JOIN notification_channel ch
            LEFT JOIN user_disabled_notification udn
                ON udn.notification_category_id = nc.notification_category_id
                AND udn.notification_channel_id = ch.notification_channel_id
                AND udn.user_id = :userId
            WHERE nc.global_enabled = true
              AND ch.global_enabled = true
            """, nativeQuery = true)
    List<UserNotificationPreference> findUserNotificationPreferences(@Param("userId") Long userId);


    Optional<UserDisabledNotification> findByUserIdAndNotificationCategoryIdAndNotificationChannelId(
            Long userId, Long notificationCategoryId, Long notificationChannelId);

    boolean existsByUserIdAndNotificationCategoryIdAndNotificationChannelId(
            Long userId, Long notificationCategoryId, Long notificationChannelId);

    void deleteByUserIdAndNotificationCategoryIdAndNotificationChannelId(
            Long userId, Long notificationCategoryId, Long notificationChannelId);
}