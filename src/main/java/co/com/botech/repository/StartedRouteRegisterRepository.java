package co.com.botech.repository;


import co.com.botech.entity.StartedRouteRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StartedRouteRegisterRepository extends JpaRepository<StartedRouteRegister, Long> {

    @Query("""
               SELECT s
               FROM StartedRouteRegister s
               WHERE s.status = :status
                 AND s.registerDate = :date
                 AND (s.operatorId = :employeeId OR s.assistantId = :employeeId)
               ORDER BY s.startTime ASC
            """)
    List<StartedRouteRegister> findByDateAndEmployeeAndStatus(
            @Param("date") LocalDate date,
            @Param("employeeId") Integer employeeId,
            @Param("status") Boolean status
    );


}