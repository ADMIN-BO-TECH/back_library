package co.com.botech.repository;

import co.com.botech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByFirebaseUid(String firebaseUid);

    @Query("SELECT DISTINCT uf.user FROM UserFamily uf WHERE uf.family.school.id = :schoolId")
    List<User> findUsersBySchool(@Param("schoolId") Long schoolId);

    @Query("SELECT DISTINCT uf.user FROM UserFamily uf WHERE uf.family.id IN :familyIds")
    List<User> findUsersByFamilies(@Param("familyIds") List<Long> familyIds);

    @Query("""
            SELECT DISTINCT uf.user FROM UserFamily uf
            WHERE uf.family.school.id = :schoolId
            AND EXISTS (
                SELECT nc FROM NotificationCategory nc
                WHERE nc.id = :categoryId
                AND nc.globalEnabled = true
            )
            AND NOT EXISTS (
                SELECT udc FROM UserDisabledCategory udc
                WHERE udc.user = uf.user
                AND udc.notificationCategory.id = :categoryId
            )
            """)
    List<User> findEnabledUsersBySchoolAndCategory(
            @Param("schoolId") Long schoolId,
            @Param("categoryId") Long categoryId);

    @Query("""
            SELECT DISTINCT uf.user FROM UserFamily uf
            WHERE uf.family.id IN :familyIds
            AND EXISTS (
                SELECT nc FROM NotificationCategory nc
                WHERE nc.id = :categoryId
                AND nc.globalEnabled = true
            )
            AND NOT EXISTS (
                SELECT udc FROM UserDisabledCategory udc
                WHERE udc.user = uf.user
                AND udc.notificationCategory.id = :categoryId
            )
            """)
    List<User> findEnabledUsersByFamiliesAndCategory(
            @Param("familyIds") List<Long> familyIds,
            @Param("categoryId") Long categoryId);
}