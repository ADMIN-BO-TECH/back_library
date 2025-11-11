package co.com.botech.repository;

import co.com.botech.entity.NotificationCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationCategoryRepository extends JpaRepository<NotificationCategory, Long> {}