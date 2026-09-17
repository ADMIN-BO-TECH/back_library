package co.com.botech.repository;

import co.com.botech.customDto.PreoperationFleetSummaryProjection;
import co.com.botech.customDto.PreoperationTrendPointProjection;
import co.com.botech.customDto.PreoperationVehicleOverviewProjection;
import co.com.botech.customDto.PreoperationVehicleRankingProjection;
import co.com.botech.entity.Preoperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PreoperationRepository extends JpaRepository<Preoperation, Long> {

    Optional<Preoperation> findByIdAndSchoolId(Long id, Long schoolId);

    Page<Preoperation> findByVehicle_IdAndSchoolIdAndPreopDateBetweenOrderByPreopDateDescPreopHourDesc(
            Long vehicleId, Long schoolId, LocalDate from, LocalDate to, Pageable pageable);

    @Query(value = """
        SELECT
            COUNT(*)                                                          AS totalPreops,
            COALESCE(SUM(CASE WHEN has_issues THEN 1 ELSE 0 END), 0)          AS preopsWithIssues,
            COALESCE(SUM(CASE WHEN has_critical THEN 1 ELSE 0 END), 0)        AS preopsWithCritical,
            COUNT(DISTINCT vehicle_id)                                        AS totalVehicles,
            COUNT(DISTINCT CASE WHEN has_issues THEN vehicle_id END)          AS vehiclesWithIssues,
            COUNT(DISTINCT CASE WHEN has_critical THEN vehicle_id END)        AS vehiclesWithCritical
        FROM preoperations
        WHERE school_id = :schoolId AND preop_date BETWEEN :from AND :to
        """, nativeQuery = true)
    PreoperationFleetSummaryProjection fleetSummary(
            @Param("schoolId") Long schoolId, @Param("from") LocalDate from, @Param("to") LocalDate to);

    // Una fila por vehiculo, con la placa/flota/colegio del registro MAS RECIENTE del
    // periodo (se congelan por registro, asi que pueden variar si el vehiculo cambio
    // de placa o de colegio dentro del rango).
    @Query(value = """
        SELECT vehicleId, plateNumber, fleetNumber, schoolName, totalPreops, preopsWithIssues, criticalCount, lastPreopDate
        FROM (
            SELECT
                p.vehicle_id AS vehicleId,
                FIRST_VALUE(p.plate_number) OVER (PARTITION BY p.vehicle_id ORDER BY p.preop_date DESC, p.preop_hour DESC) AS plateNumber,
                FIRST_VALUE(p.fleet_number) OVER (PARTITION BY p.vehicle_id ORDER BY p.preop_date DESC, p.preop_hour DESC) AS fleetNumber,
                FIRST_VALUE(p.school_name) OVER (PARTITION BY p.vehicle_id ORDER BY p.preop_date DESC, p.preop_hour DESC) AS schoolName,
                COUNT(*) OVER (PARTITION BY p.vehicle_id) AS totalPreops,
                SUM(CASE WHEN p.has_issues THEN 1 ELSE 0 END) OVER (PARTITION BY p.vehicle_id) AS preopsWithIssues,
                SUM(CASE WHEN p.has_critical THEN 1 ELSE 0 END) OVER (PARTITION BY p.vehicle_id) AS criticalCount,
                MAX(p.preop_date) OVER (PARTITION BY p.vehicle_id) AS lastPreopDate,
                ROW_NUMBER() OVER (PARTITION BY p.vehicle_id ORDER BY p.preop_date DESC, p.preop_hour DESC) AS rn
            FROM preoperations p
            WHERE p.school_id = :schoolId AND p.preop_date BETWEEN :from AND :to
        ) t
        WHERE rn = 1
        ORDER BY preopsWithIssues DESC
        """, nativeQuery = true)
    List<PreoperationVehicleRankingProjection> vehiclesRanking(
            @Param("schoolId") Long schoolId, @Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query(value = """
        SELECT
            agg.vehicle_id       AS vehicleId,
            latest.plate_number  AS plateNumber,
            latest.fleet_number  AS fleetNumber,
            latest.school_name   AS schoolName,
            latest.mileage       AS lastKilometrage,
            latest.preop_date    AS lastPreopDate,
            latest.operator_name AS lastOperator,
            agg.totalPreops      AS totalPreops,
            agg.preopsWithIssues AS preopsWithIssues,
            agg.preopsWithCritical AS preopsWithCritical
        FROM (
            SELECT vehicle_id,
                   COUNT(*) AS totalPreops,
                   SUM(CASE WHEN has_issues THEN 1 ELSE 0 END) AS preopsWithIssues,
                   SUM(CASE WHEN has_critical THEN 1 ELSE 0 END) AS preopsWithCritical
            FROM preoperations
            WHERE school_id = :schoolId AND vehicle_id = :vehicleId AND preop_date BETWEEN :from AND :to
            GROUP BY vehicle_id
        ) agg
        JOIN preoperations latest ON latest.id = (
            SELECT p2.id FROM preoperations p2
            WHERE p2.vehicle_id = agg.vehicle_id AND p2.school_id = :schoolId
              AND p2.preop_date BETWEEN :from AND :to
            ORDER BY p2.preop_date DESC, p2.preop_hour DESC
            LIMIT 1
        )
        """, nativeQuery = true)
    Optional<PreoperationVehicleOverviewProjection> vehicleOverview(
            @Param("schoolId") Long schoolId, @Param("vehicleId") Long vehicleId,
            @Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query(value = """
        SELECT
            preop_date AS bucketDate,
            COUNT(*) AS totalPreops,
            SUM(CASE WHEN has_issues THEN 1 ELSE 0 END) AS preopsWithIssues,
            SUM(CASE WHEN has_critical THEN 1 ELSE 0 END) AS preopsWithCritical
        FROM preoperations
        WHERE school_id = :schoolId AND vehicle_id = :vehicleId AND preop_date BETWEEN :from AND :to
        GROUP BY preop_date
        ORDER BY preop_date
        """, nativeQuery = true)
    List<PreoperationTrendPointProjection> trendByDay(
            @Param("schoolId") Long schoolId, @Param("vehicleId") Long vehicleId,
            @Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query(value = """
        SELECT
            DATE_SUB(preop_date, INTERVAL WEEKDAY(preop_date) DAY) AS bucketDate,
            COUNT(*) AS totalPreops,
            SUM(CASE WHEN has_issues THEN 1 ELSE 0 END) AS preopsWithIssues,
            SUM(CASE WHEN has_critical THEN 1 ELSE 0 END) AS preopsWithCritical
        FROM preoperations
        WHERE school_id = :schoolId AND vehicle_id = :vehicleId AND preop_date BETWEEN :from AND :to
        GROUP BY DATE_SUB(preop_date, INTERVAL WEEKDAY(preop_date) DAY)
        ORDER BY bucketDate
        """, nativeQuery = true)
    List<PreoperationTrendPointProjection> trendByWeek(
            @Param("schoolId") Long schoolId, @Param("vehicleId") Long vehicleId,
            @Param("from") LocalDate from, @Param("to") LocalDate to);

    @Query(value = """
        SELECT COUNT(*) FROM preoperations
        WHERE school_id = :schoolId AND preop_date BETWEEN :from AND :to
          AND (:vehicleId IS NULL OR vehicle_id = :vehicleId)
          AND internal_notes IS NOT NULL AND TRIM(internal_notes) <> ''
        """, nativeQuery = true)
    long countWithInternalNotes(@Param("schoolId") Long schoolId, @Param("from") LocalDate from,
                                 @Param("to") LocalDate to, @Param("vehicleId") Long vehicleId);

    @Query(value = """
        SELECT COUNT(*) FROM preoperations
        WHERE school_id = :schoolId AND preop_date BETWEEN :from AND :to
          AND (:vehicleId IS NULL OR vehicle_id = :vehicleId)
          AND documents_notes IS NOT NULL AND TRIM(documents_notes) <> ''
        """, nativeQuery = true)
    long countWithDocumentsNotes(@Param("schoolId") Long schoolId, @Param("from") LocalDate from,
                                  @Param("to") LocalDate to, @Param("vehicleId") Long vehicleId);
}
