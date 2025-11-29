package co.com.botech.repository;

import co.com.botech.entity.SchoolEmployee;
import co.com.botech.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchoolEmployeeRepository extends JpaRepository<SchoolEmployee, Long> {
    boolean existsByDocumentNumberAndSchool_Id(String documentNumber, Long schoolId);
    boolean existsByDocumentNumberAndId(String documentNumber, Long employeeId);

    Optional<SchoolEmployee> findByDocumentNumberAndSchool_id(String documentNumber, Long schoolId);

    List<SchoolEmployee> findBySchool_Id(Long schoolId);
}