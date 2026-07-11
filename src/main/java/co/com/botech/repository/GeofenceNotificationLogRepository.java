package co.com.botech.repository;

import co.com.botech.entity.GeofenceNotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeofenceNotificationLogRepository extends JpaRepository<GeofenceNotificationLog, Long> {
}
