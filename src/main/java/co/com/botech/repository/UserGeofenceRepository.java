package co.com.botech.repository;

import co.com.botech.entity.UserGeofence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserGeofenceRepository extends JpaRepository<UserGeofence, Long> {
    List<UserGeofence> findByUserId(Long userId);
    long countByUserId(Long userId);
}
