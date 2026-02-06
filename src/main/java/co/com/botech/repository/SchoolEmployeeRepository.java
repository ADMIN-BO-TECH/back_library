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

    List<SchoolEmployee> findBySchool_Id(Long schoolId);
    @Query("""
        SELECT ap
        FROM SchoolEmployee ap
        WHERE ap.family.id = :familyId
    """)
    List<SchoolEmployee> findByFamilyCode_Id(@Param("familyId") Long familyId);
}