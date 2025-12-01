package co.com.botech.repository;

import co.com.botech.entity.RfidRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RfidRegisterRepository extends JpaRepository<RfidRegister, Long> {
    @Query("SELECT DISTINCT r.description FROM RfidRegister r WHERE r.school.id = :schoolId")
    List<String> findDistinctReceptionBySchool_Id(@Param("schoolId") Long schoolId);
}
