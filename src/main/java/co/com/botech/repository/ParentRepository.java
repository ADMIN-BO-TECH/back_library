package co.com.botech.repository;

import co.com.botech.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    boolean existsByDocumentNumberAndSchool_Id(String documentNumber, Long schoolId);

    boolean existsByDocumentNumberAndId(String documentNumber, Long employeeId);

    Optional<Parent> findByDocumentNumberAndSchool_id(String documentNumber, Long schoolId);

    @Query("SELECT p FROM Parent p WHERE p.school.id = :schoolId AND p.active = true")
    List<Parent> findBySchool_Id(@Param("schoolId") Long schoolId);

    @Query("""
        SELECT p
        FROM Parent p
        WHERE p.family.familyCode = :familyCode
          AND p.active = true
    """)
    List<Parent> findByFamilyCode_Code(@Param("familyCode") String familyCode);
}
