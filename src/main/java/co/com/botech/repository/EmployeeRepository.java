package co.com.botech.repository;

import co.com.botech.entity.Employee;
import co.com.botech.entity.SchoolEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByDocumentNumber(String documentNumber);
    boolean existsByDocumentNumberAndId(String documentNumber, Long employeeId);
    Optional<Employee> findByDocumentNumber(String documentNumber);
}
