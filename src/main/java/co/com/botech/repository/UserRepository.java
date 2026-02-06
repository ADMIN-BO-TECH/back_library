package co.com.botech.repository;

import co.com.botech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
    SELECT DISTINCT u
    FROM User u
    JOIN UserNotificationPreferences up ON up.user.id = u.id
    JOIN UserFamily uf ON uf.user.id = u.id
    JOIN uf.family f
    WHERE f.school.id = :schoolId
      AND up.enabled = true
      AND up.category.id = :notificationCategoryId
""")
    List<User> findTokenBySchool_IdAndActivePreference(@Param("schoolId") Long schoolId,
                                                       @Param("notificationCategoryId") Long notificationCategoryId);

    @Query("""
    SELECT DISTINCT u
    FROM User u
    JOIN UserNotificationPreferences up ON up.user.id = u.id
    JOIN UserFamily uf ON uf.user.id = u.id
    JOIN uf.family f
    WHERE f.school.id = :schoolId
      AND up.enabled = true
      AND up.category.id = :notificationCategoryId
      AND f.id IN :familyCodeList
""")
    List<User> findTokenBySchool_IdAndActivePreferenceAndFamilyCode(@Param("schoolId") Long schoolId,
                                                                    @Param("notificationCategoryId") Long notificationCategoryId,
                                                                    @Param("familyCodeList") List<Long> familyCodeList);
}