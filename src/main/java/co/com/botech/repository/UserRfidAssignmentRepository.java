package co.com.botech.repository;

import co.com.botech.entity.UserRfidAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRfidAssignmentRepository extends JpaRepository<UserRfidAssignment, Long> {
    List<UserRfidAssignment> findByUser_FirebaseUid(String firebaseUid);
    boolean existsByUser_FirebaseUid(String firebaseUid);
}
