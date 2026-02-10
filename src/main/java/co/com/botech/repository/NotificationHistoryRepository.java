package co.com.botech.repository;

import co.com.botech.entity.NotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long> {
    List<NotificationHistory> findTop30ByUser_FirebaseUidOrderBySentAtDesc(String firebaseUid);
}
