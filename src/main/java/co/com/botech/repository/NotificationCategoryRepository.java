package co.com.botech.repository;

import co.com.botech.entity.NotificationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NotificationCategoryRepository extends JpaRepository<NotificationCategory, Long> {

    Optional<NotificationCategory> findByCategoryName(String categoryName);
}