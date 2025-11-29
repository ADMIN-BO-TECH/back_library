package co.com.botech.repository;

import co.com.botech.entity.SchoolEmployee;
import co.com.botech.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolEmployeeRepository extends JpaRepository<SchoolEmployee, Long> {
    boolean existsByDocumentNumberAndSchool_Id(String documentNumber, Long schoolId);
    boolean existsByDocumentNumberAndId(String documentNumber, Long employeeId);

    List<SchoolEmployee> findBySchool_Id(Long schoolId);
}