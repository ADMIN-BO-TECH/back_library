package co.com.botech.repository;

import co.com.botech.entity.Permits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PermitRepository extends JpaRepository<Permits, Long> {
    List<Permits> findByStudent_Family_FamilyCodeAndStudent_School_Id(String familyCode, Long schoolId);

    @Query("""
        SELECT p
        FROM Permits p
        WHERE p.student.family.school.id = :schoolId
    """)
    List<Permits> findByStudent_School_Id(@Param("schoolId") Long schoolId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("""
        UPDATE Permits AS pe
        SET pe.permitStatus = :finalStatus,
            pe.response = :response,
            pe.repliedBy = :repliedBy
        WHERE pe.id = :permitId
    """)
    int updateByStatus(@Param("permitId") Long permitId,
                       @Param("finalStatus") String finalStatus,
                       @Param("response") String response,
                       @Param("repliedBy") String repliedBy);

    @Query("""
        SELECT p
        FROM Permits p
        WHERE p.student.family.id = :familyCodeId
          AND p.student.family.school.id = :schoolId
    """)
    List<Permits> findByStudent_FamilyCode_IdAndStudent_School_Id(@Param("familyCodeId") Long familyCodeId,
                                                                  @Param("schoolId") Long schoolId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("""
        UPDATE Permits AS pe
        SET pe.permitStatus = :finalStatus
        WHERE pe.id = :permitId
    """)
    int updateStatus(@Param("permitId") Long permitId,
                     @Param("finalStatus") String finalStatus);
}