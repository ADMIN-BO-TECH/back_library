package co.com.botech.repository;

import co.com.botech.entity.LinkManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LinkManagementRepository extends JpaRepository<LinkManagement, Long> {
    List<LinkManagement> findByStatus(boolean status);

    @Modifying(clearAutomatically = true)
    @Query("""
                UPDATE LinkManagement AS lm
                SET lm.status = :finalStatus
                WHERE lm.status = :searchedStatus
            """)
    int updateByStatus(@Param("searchedStatus") Boolean searchedStatus,
                           @Param("finalStatus") Boolean finalStatus);
}
