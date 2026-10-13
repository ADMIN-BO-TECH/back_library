package co.com.botech.repository;

import co.com.botech.entity.EmployeeGuestAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeGuestAuthorizationRepository extends JpaRepository<EmployeeGuestAuthorization, Long> {

    @Query("""
            SELECT ega
            FROM EmployeeGuestAuthorization ega
            JOIN FETCH ega.authorizedPerson ap
            LEFT JOIN FETCH ap.documentType
            WHERE ega.schoolEmployee.id = :employeeId
              AND ega.active = true
            ORDER BY ega.authorizationEnd DESC
            """)
    List<EmployeeGuestAuthorization> findActiveByEmployeeId(@Param("employeeId") Long employeeId);

    Optional<EmployeeGuestAuthorization> findByIdAndSchoolEmployee_IdAndActiveTrue(Long id, Long employeeId);

    boolean existsByAuthorizedPerson_IdAndSchoolEmployee_IdAndActiveTrue(Long authorizedPersonId, Long employeeId);

    @Query("""
            SELECT ega
            FROM EmployeeGuestAuthorization ega
            WHERE ega.authorizedPerson.id = :authorizedPersonId
              AND ega.active = true
              AND ega.schoolEmployee.active = true
              AND ega.authorizationEnd >= :today
            """)
    List<EmployeeGuestAuthorization> findVigentByAuthorizedPersonId(
            @Param("authorizedPersonId") Long authorizedPersonId,
            @Param("today") LocalDate today);
}
