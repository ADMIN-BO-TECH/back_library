package co.com.botech.repository;

import co.com.botech.entity.UserFamily;
import co.com.botech.entity.UserFamilyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserFamilyRepository extends JpaRepository<UserFamily, UserFamilyId> {

    boolean existsByUser_IdAndFamily_Id(Long userId, Long familyId);

    @Query("""
        select distinct f.familyCode
        from UserFamily uf
        join uf.user u
        join uf.family f
        where u.firebaseUid = :firebaseUid
          and f.school.id = :schoolId
    """)
    List<String> findFamilyCodesByFirebaseUidAndSchoolId(
            @Param("firebaseUid") String firebaseUid,
            @Param("schoolId") Long schoolId
    );
}