package co.com.botech.repository;

import co.com.botech.entity.Parent;
import co.com.botech.entity.SchoolEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    boolean existsByDocumentNumberAndSchool_Id(String documentNumber, Long schoolId);
    boolean existsByDocumentNumberAndId(String documentNumber, Long employeeId);
    Optional<Parent> findByDocumentNumberAndSchool_id(String documentNumber, Long schoolId);

    List<Parent> findBySchool_Id(Long schoolId);
}