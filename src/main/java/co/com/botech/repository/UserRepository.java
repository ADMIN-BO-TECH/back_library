package co.com.botech.repository;

import co.com.botech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT DISTINCT u.fcmToken FROM User u " +
            "JOIN UserNotificationPreferences up ON  up.user.id = u.id WHERE " +
            "(u.school.id = :schoolId) AND " +
            "(up.enabled = true) AND " +
            "(up.category.id = :notificationCategoryId)")
    List<String> findTokenBySchool_IdAndActivePreference(@Param("schoolId") Long schoolId,
                                                      @Param("notificationCategoryId") Long notificationCategoryId);

    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN UserNotificationPreferences up ON  up.user.id = u.id WHERE " +
            "(u.school.id = :schoolId) AND " +
            "(up.enabled = true) AND " +
            "(up.category.id = :notificationCategoryId) AND " +
            "(u.familyCode.id IN :familyCodeList)")
    List<String> findTokenBySchool_IdAndActivePreferenceAndFamilyCode(@Param("schoolId") Long schoolId,
                                                                   @Param("notificationCategoryId") Long notificationCategoryId,
                                                                   @Param("familyCodeList") List<Long> familyCodeList);
}