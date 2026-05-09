package co.com.botech.repository;

import co.com.botech.entity.NotificationCategory;
import co.com.botech.entity.User;
import co.com.botech.entity.UserDisabledCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDisabledCategoryRepository extends JpaRepository<UserDisabledCategory, Long> {

    Optional<UserDisabledCategory> findByUserIdAndNotificationCategoryId(Long userId, Long notificationCategoryId);

    boolean existsByUserIdAndNotificationCategoryId(Long userId, Long notificationCategoryId);

    void deleteByUserIdAndNotificationCategoryId(Long userId, Long notificationCategoryId);
}