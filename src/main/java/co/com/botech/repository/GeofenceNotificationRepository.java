package co.com.botech.repository;

import co.com.botech.entity.GeofenceNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeofenceNotificationRepository extends JpaRepository<GeofenceNotification, Long> {
}
