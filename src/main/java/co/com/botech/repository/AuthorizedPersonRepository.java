package co.com.botech.repository;

import co.com.botech.entity.AuthorizedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorizedPersonRepository extends JpaRepository<AuthorizedPerson, Long> {

    boolean existsByDocumentNumberAndSchool_Id(String documentNumber, Long schoolId);

    boolean existsByDocumentNumberAndId(String documentNumber, Long employeeId);

    Optional<AuthorizedPerson> findByDocumentNumberAndSchool_id(String documentNumber, Long schoolId);

    @Query("SELECT ap FROM AuthorizedPerson ap WHERE ap.school.id = :schoolId AND ap.active = true")
    List<AuthorizedPerson> findBySchool_Id(@Param("schoolId") Long schoolId);

    @Query("""
        SELECT ap
        FROM AuthorizedPerson ap
        WHERE ap.family.id = :familyId
          AND ap.active = true
    """)
    List<AuthorizedPerson> findByFamilyCode_Id(@Param("familyId") Long familyId);

    List<AuthorizedPerson> findBySchool_IdAndCreatedBySchoolEmployee_IdAndActiveTrue(Long schoolId, Long employeeId);

    List<AuthorizedPerson> findBySchool_IdAndFamily_IdAndActiveTrue(Long schoolId, Long familyId);

    @Query("""
        SELECT DISTINCT ap FROM AuthorizedPerson ap
        WHERE ap.school.id = :schoolId
          AND ap.active = true
          AND (ap.createdBySchoolEmployee.id = :employeeId
               OR (:familyId IS NOT NULL AND ap.family.id = :familyId))
    """)
    List<AuthorizedPerson> findVisibleForEmployee(@Param("schoolId") Long schoolId,
                                                  @Param("employeeId") Long employeeId,
                                                  @Param("familyId") Long familyId);
}
