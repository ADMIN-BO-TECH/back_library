package co.com.botech.repository;

import co.com.botech.entity.AuthorizedPerson;
import co.com.botech.entity.SchoolEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorizedPersonRepository extends JpaRepository<AuthorizedPerson, Long> {
    boolean existsByDocumentNumberAndSchool_Id(String documentNumber, Long schoolId);
    boolean existsByDocumentNumberAndId(String documentNumber, Long employeeId);
    Optional<AuthorizedPerson> findByDocumentNumberAndSchool_id(String documentNumber, Long schoolId);
    List<AuthorizedPerson> findBySchool_Id(Long schoolId);
    @Query("""
        SELECT ap
        FROM AuthorizedPerson ap
        WHERE ap.family.id = :familyId
    """)
    List<AuthorizedPerson> findByFamilyCode_Id(@Param("familyId") Long familyId);
}