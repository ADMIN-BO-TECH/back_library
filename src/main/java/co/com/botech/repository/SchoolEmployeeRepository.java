package co.com.botech.repository;

import co.com.botech.entity.SchoolEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SchoolEmployeeRepository extends JpaRepository<SchoolEmployee, Long> {

    boolean existsByDocumentNumberAndSchool_Id(String documentNumber, Long schoolId);

    boolean existsByDocumentNumberAndId(String documentNumber, Long employeeId);

    Optional<SchoolEmployee> findByDocumentNumberAndSchool_id(String documentNumber, Long schoolId);

    @Query("SELECT e FROM SchoolEmployee e WHERE e.school.id = :schoolId AND e.active = true")
    List<SchoolEmployee> findBySchool_Id(@Param("schoolId") Long schoolId);

    @Query("""
        SELECT ap
        FROM SchoolEmployee ap
        WHERE ap.family.id = :familyId
          AND ap.active = true
    """)
    List<SchoolEmployee> findByFamilyCode_Id(@Param("familyId") Long familyId);

    Optional<SchoolEmployee> findFirstBySchool_IdAndFamily_FamilyCodeInOrderByIdAsc(
            Long schoolId,
            List<String> familyCodes
    );
}
