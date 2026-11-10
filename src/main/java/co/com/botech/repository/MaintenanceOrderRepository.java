package co.com.botech.repository;

import co.com.botech.entity.MaintenanceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MaintenanceOrderRepository extends JpaRepository<MaintenanceOrder, Long> {

    Optional<MaintenanceOrder> findByIdAndActiveTrue(Long id);

    // Para validar acceso sin cargar la entidad completa
    @Query("SELECT o.requestedByUserId FROM MaintenanceOrder o WHERE o.id = :id AND o.active = true")
    Optional<Long> findRequesterUserId(@Param("id") Long id);
}
