package co.com.botech.repository;

import co.com.botech.customDto.MaintenanceOrderStatusHistoryProjection;
import co.com.botech.entity.MaintenanceOrderStatusHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Extiende Repository (no JpaRepository) a propósito: el historial es de solo INSERT y aquí
 * no se expone delete ni update. Solo save y la lectura ordenada.
 */
public interface MaintenanceOrderStatusHistoryRepository extends Repository<MaintenanceOrderStatusHistory, Long> {

    MaintenanceOrderStatusHistory save(MaintenanceOrderStatusHistory entry);

    // Nativa: un LEFT JOIN a users resuelve el nombre sin pasar por @SQLRestriction("enabled = true"),
    // así un coordinador deshabilitado después conserva su nombre. id desempata mismo changed_at.
    @Query(value = """
        SELECT h.id                 AS id,
               h.from_status        AS fromStatus,
               h.to_status          AS toStatus,
               h.changed_by_user_id AS changedByUserId,
               h.changed_by_role    AS changedByRole,
               h.changed_at         AS changedAt,
               h.comment            AS comment,
               u.first_name         AS changedByFirstName,
               u.last_name          AS changedByLastName
        FROM maintenance_order_status_history h
        LEFT JOIN users u ON u.user_id = h.changed_by_user_id
        WHERE h.maintenance_order_id = :orderId
        ORDER BY h.changed_at ASC, h.id ASC
        """, nativeQuery = true)
    List<MaintenanceOrderStatusHistoryProjection> findTimelineByOrderId(@Param("orderId") Long orderId);
}
