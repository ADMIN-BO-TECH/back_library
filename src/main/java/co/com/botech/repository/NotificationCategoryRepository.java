package co.com.botech.repository;

import co.com.botech.entity.NotificationCategory;
import co.com.botech.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationCategoryRepository extends JpaRepository<NotificationCategory, Long> {
    Optional<NotificationCategory> findByCategoryName(String categoryName);
}