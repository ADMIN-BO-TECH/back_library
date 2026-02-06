package co.com.botech.repository;

import co.com.botech.entity.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
    @Query("SELECT a FROM Authorization a WHERE a.authorizedPerson.id = :authorizedPersonId AND (NOW() BETWEEN a.authorizationStartDate AND a.authorizationEndDate)")
    List<Authorization> findDateActiveAuthorizationsByAuthorizedPersonId(@Param("authorizedPersonId") Long authorizedPersonId);

    @Query("SELECT a FROM Authorization a WHERE a.authorizedPerson.school.id = :schoolId")
    List<Authorization> findBySchool_Id(Long schoolId);

    @Query("SELECT a.authorizationStartDate as dateValue FROM Authorization a " +
            "WHERE (a.authorizedPerson.id = :authorizedPersonId) " +
            "AND a.authorizationEndDate > NOW() " +
            "UNION ALL " +
            "SELECT  a.authorizationEndDate as dateValue FROM Authorization a " +
            "WHERE (a.authorizedPerson.id = :authorizedPersonId) " +
            "AND a.authorizationEndDate > NOW() ")
    List<LocalDate> findCurrentAuthorizationsByAuthorizedPersonId(Long authorizedPersonId);

    @Query("SELECT DISTINCT a FROM Authorization a WHERE a.student.id = :idRecordStudent")
    List<Authorization> findAuthorizationsByStudent(@Param("idRecordStudent") Long idRecordStudent);

    @Query("""
    SELECT DISTINCT a
    FROM Authorization a
    WHERE a.student.family.familyCode = :familyCode
      AND a.student.family.school.id = :schoolId
""")
    List<Authorization> findAuthorizationByFamilyCodeAndSchoolId(
            @Param("familyCode") String familyCode,
            @Param("schoolId") Long schoolId);


    @Query("""
                SELECT DISTINCT a
                FROM Authorization a
                WHERE a.student.id = :studentId
                ORDER BY
                  CASE
                    WHEN a.authorizationStartDate <= CURRENT_TIMESTAMP
                     AND a.authorizationEndDate   >= CURRENT_TIMESTAMP
                    THEN 0
                    ELSE 1
                  END,
                  a.authorizationEndDate DESC
            """)
    List<Authorization> findLastActiveOrExpiredAuthorizationsByStudentId(
            @Param("studentId") Long studentId,
            Pageable pageable
    );

}