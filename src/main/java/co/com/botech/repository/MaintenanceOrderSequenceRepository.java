package co.com.botech.repository;

import co.com.botech.entity.MaintenanceOrderSequence;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MaintenanceOrderSequenceRepository extends JpaRepository<MaintenanceOrderSequence, Integer> {

    // SELECT ... FOR UPDATE: serializa la generación del código dentro de la transacción de creación
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM MaintenanceOrderSequence s WHERE s.seqYear = :year")
    Optional<MaintenanceOrderSequence> lockByYear(@Param("year") Integer year);
}
