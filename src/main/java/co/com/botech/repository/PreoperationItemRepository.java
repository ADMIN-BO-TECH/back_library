package co.com.botech.repository;

import co.com.botech.customDto.PreoperationCategoryIssueProjection;
import co.com.botech.customDto.PreoperationInternalBreakdownProjection;
import co.com.botech.customDto.PreoperationItemBreakdownProjection;
import co.com.botech.customDto.PreoperationTopIssueProjection;
import co.com.botech.entity.PreoperationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PreoperationItemRepository extends JpaRepository<PreoperationItem, Long> {

    List<PreoperationItem> findByPreoperation_Id(Long preoperationId);

    @Query(value = """
        SELECT i.item_key AS itemKey, i.status AS status, COUNT(*) AS total
        FROM preoperation_items i
        JOIN preoperations p ON p.id = i.preoperation_id
        WHERE i.category = :category
          AND p.preop_date BETWEEN :from AND :to
          AND (:vehicleId IS NULL OR p.vehicle_id = :vehicleId)
        GROUP BY i.item_key, i.status
        """, nativeQuery = true)
    List<PreoperationItemBreakdownProjection> itemBreakdown(
            @Param("category") String category, @Param("from") LocalDate from,
            @Param("to") LocalDate to, @Param("vehicleId") Long vehicleId);

    @Query(value = """
        SELECT i.item_key AS itemKey, i.status AS status, i.fluid_level AS fluidLevel, COUNT(*) AS total
        FROM preoperation_items i
        JOIN preoperations p ON p.id = i.preoperation_id
        WHERE i.category = 'INTERNA'
          AND p.preop_date BETWEEN :from AND :to
          AND (:vehicleId IS NULL OR p.vehicle_id = :vehicleId)
        GROUP BY i.item_key, i.status, i.fluid_level
        """, nativeQuery = true)
    List<PreoperationInternalBreakdownProjection> internalBreakdown(
            @Param("from") LocalDate from, @Param("to") LocalDate to, @Param("vehicleId") Long vehicleId);

    @Query(value = """
        SELECT p.vehicle_id AS vehicleId, i.category AS category, i.item_key AS itemKey, COUNT(*) AS issueCount
        FROM preoperation_items i
        JOIN preoperations p ON p.id = i.preoperation_id
        WHERE p.preop_date BETWEEN :from AND :to AND i.is_issue = true
        GROUP BY p.vehicle_id, i.category, i.item_key
        """, nativeQuery = true)
    List<PreoperationTopIssueProjection> topIssuesByVehicle(
            @Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query(value = """
        SELECT i.item_key AS itemKey, COUNT(*) AS issueCount
        FROM preoperation_items i
        JOIN preoperations p ON p.id = i.preoperation_id
        WHERE p.vehicle_id = :vehicleId
          AND p.preop_date BETWEEN :from AND :to AND i.category = :category AND i.is_issue = true
        GROUP BY i.item_key
        """, nativeQuery = true)
    List<PreoperationCategoryIssueProjection> topIssuesForVehicle(
            @Param("vehicleId") Long vehicleId, @Param("from") LocalDate from,
            @Param("to") LocalDate to, @Param("category") String category);
}
