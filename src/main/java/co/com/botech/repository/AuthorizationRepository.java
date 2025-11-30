package co.com.botech.repository;

import co.com.botech.entity.Authorization;
import co.com.botech.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
    @Query("SELECT a FROM Authorization a WHERE a.id = :authorizedPersonId AND (NOW() BETWEEN a.authorizationStartDate AND a.authorizationEndDate)")
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
}