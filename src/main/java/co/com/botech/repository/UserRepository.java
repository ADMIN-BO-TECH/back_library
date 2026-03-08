package co.com.botech.repository;

import co.com.botech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
        SELECT DISTINCT u
        FROM User u
        JOIN UserNotificationPreferences up ON up.user = u
        JOIN UserFamily uf ON uf.user = u
        JOIN uf.family f
        WHERE f.school.id = :schoolId
          AND up.enabled = true
          AND up.category.id = :notificationCategoryId
    """)
    List<User> findUsersWithActivePreferenceBySchool(
            @Param("schoolId") Long schoolId,
            @Param("notificationCategoryId") Long notificationCategoryId
    );

    @Query("""
        SELECT DISTINCT u
        FROM User u
        JOIN UserNotificationPreferences up ON up.user = u
        JOIN UserFamily uf ON uf.user = u
        JOIN uf.family f
        WHERE f.school.id = :schoolId
          AND up.enabled = true
          AND up.category.id = :notificationCategoryId
          AND f.id IN :familyIdList
    """)
    List<User> findUsersWithActivePreferenceBySchoolAndFamilies(
            @Param("schoolId") Long schoolId,
            @Param("notificationCategoryId") Long notificationCategoryId,
            @Param("familyIdList") List<Long> familyIdList
    );

    Optional<User> findByFirebaseUid(String firebaseUid);
}