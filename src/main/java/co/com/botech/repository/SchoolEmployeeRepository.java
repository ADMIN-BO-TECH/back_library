package co.com.botech.repository;

import co.com.botech.entity.SchoolEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolEmployeeRepository extends JpaRepository<SchoolEmployee, Long> {}