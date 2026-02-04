package co.com.botech.repository;

import co.com.botech.entity.Authorization;
import co.com.botech.entity.Permits;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PermitRepository extends JpaRepository<Permits, Long> {

    List<Permits> findByStudent_School_Id(Long schoolId);
    @Modifying(clearAutomatically = true)
    @Query("""
                UPDATE Permits AS pe
                SET pe.permitStatus = :finalStatus,
                pe.response = :response,
                pe.repliedBy =:repliedBy
                WHERE pe.id = :permitId
            """)
    int updateByStatus(@Param("permitId") Long permitId,
                       @Param("finalStatus") String finalStatus,
                       @Param("response") String response,
                       @Param("repliedBy") String repliedBy);

    List<Permits> findByStudent_FamilyCode_IdAndStudent_School_Id(Long familyCodeId, Long schoolId);

    @Modifying(clearAutomatically = true)
    @Query("""
                UPDATE Permits AS pe
                SET pe.permitStatus = :finalStatus
                WHERE pe.id = :permitId
            """)
    int updateStatus(@Param("permitId") Long permitId,
                       @Param("finalStatus") String finalStatus);
}