package co.com.botech.repository;

import co.com.botech.entity.MaintenanceAffectedArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaintenanceAffectedAreaRepository extends JpaRepository<MaintenanceAffectedArea, Long> {

    List<MaintenanceAffectedArea> findByActiveTrueOrderBySortOrderAscNameAsc();

    Optional<MaintenanceAffectedArea> findByIdAndActiveTrue(Long id);
}
