package co.com.botech.repository;

import co.com.botech.customDto.*;
import co.com.botech.entity.*;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {

    default Page<Attendance> getAttendanceByCustomQuery(
            List<String> filterIdTerms,
            List<String> generalFilters,
            List<String> userTypeTerms,
            List<String> filterRfidRegisterTerms,
            List<String> typeAttendanceFilterTerms,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable,
            Long schoolId
    ) {

        Specification<Attendance> attendanceSpecification = (attendance, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Attendance, AuthorizedPerson> authorizedPersonJoin = attendance.join("authorizedPerson", JoinType.LEFT);
            Join<Attendance, Parent> parentJoin = attendance.join("parent", JoinType.LEFT);
            Join<Attendance, Student> studentJoin = attendance.join("student", JoinType.LEFT);
            Join<Attendance, SchoolEmployee> employeeJoin = attendance.join("schoolEmployee", JoinType.LEFT);
            Join<Attendance, RfidRegister> rfidJoin = attendance.join("rfidRegister", JoinType.LEFT);
            Join<Attendance, AttendanceType> attendanceTypeJoin = attendance.join("type", JoinType.LEFT);
            Join<RfidRegister, KindDevice> kindJoin = rfidJoin.join("kindDevice", JoinType.LEFT);
            Join<Attendance, School> schoolJoin = attendance.join("school", JoinType.LEFT);

            // ID
            if (filterIdTerms != null && !filterIdTerms.isEmpty()) {
                predicates.add(builder.or(filterIdTerms.stream()
                        .map(filterValue -> builder.like(
                                attendance.get("id").as(String.class),
                                "%" + filterValue + "%"
                        )).toArray(Predicate[]::new))
                );
            }

            // USER TYPE
            if (userTypeTerms != null && !userTypeTerms.isEmpty()) {
                predicates.add(builder.or(userTypeTerms.stream()
                        .map(filterValue -> builder.like(
                                builder.lower(attendance.get("userType")),
                                "%" + filterValue.toLowerCase() + "%"
                        )).toArray(Predicate[]::new))
                );
            }

            // GLOBAL FILTER
            if (generalFilters != null && !generalFilters.isEmpty()) {
                List<Predicate> generalPredicate = new ArrayList<>();
                for (String specificGeneralFilter : generalFilters) {
                    String value = "%" + specificGeneralFilter.toLowerCase() + "%";
                    Predicate generalPredicateFinal = builder.or(
                            // AUTHORIZED PERSON
                            builder.like(builder.lower(authorizedPersonJoin.get("firstName")), value),
                            builder.like(builder.lower(authorizedPersonJoin.get("lastName")), value),
                            builder.like(builder.lower(authorizedPersonJoin.get("documentNumber")), value),

                            // PARENT
                            builder.like(builder.lower(parentJoin.get("firstName")), value),
                            builder.like(builder.lower(parentJoin.get("lastName")), value),
                            builder.like(builder.lower(parentJoin.get("documentNumber")), value),

                            // STUDENT
                            builder.like(builder.lower(studentJoin.get("firstName")), value),
                            builder.like(builder.lower(studentJoin.get("lastName")), value),
                            builder.like(builder.lower(studentJoin.get("gradeLevel")), value),
                            builder.like(studentJoin.get("studentId").as(String.class), value),

                            // EMPLOYEE
                            builder.like(builder.lower(employeeJoin.get("firstName")), value),
                            builder.like(builder.lower(employeeJoin.get("lastName")), value),
                            builder.like(builder.lower(employeeJoin.get("documentNumber")), value)
                    );
                    generalPredicate.add(generalPredicateFinal);
                }

                predicates.add(builder.or(generalPredicate.toArray(Predicate[]::new)));
            }

            // RFID REGISTER
            if (filterRfidRegisterTerms != null && !filterRfidRegisterTerms.isEmpty()) {
                List<Predicate> rfidPredicate = new ArrayList<>();
                for (String t : filterRfidRegisterTerms) {
                    String like = "%" + t.toLowerCase() + "%";
                    Expression<String> concat = builder.lower(builder.concat(
                                    builder.coalesce(rfidJoin.get("description"), ""),
                                    builder.concat(" ", builder.concat(
                                            builder.coalesce(rfidJoin.get("rfidTag"), ""),
                                            builder.concat(" ",
                                                    builder.coalesce(kindJoin.get("description"), ""))))
                            )
                    );

                    rfidPredicate.add(builder.like(concat, like));
                }

                predicates.add(builder.or(rfidPredicate.toArray(Predicate[]::new)));
            }

            // TYPE
            if (typeAttendanceFilterTerms != null && !typeAttendanceFilterTerms.isEmpty()) {
                predicates.add(builder.or(typeAttendanceFilterTerms.stream()
                                .map(filterValue -> builder.like(
                                        builder.lower(attendanceTypeJoin.get("type").get("description")),
                                        "%" + filterValue.toLowerCase() + "%"
                                ))
                                .toArray(Predicate[]::new)
                        )
                );
            }

            // DATE
            if (startDate != null) {
                predicates.add(builder.greaterThanOrEqualTo(attendance.get("attendanceTime"), startDate));
            }

            if (endDate != null) {
                predicates.add(builder.lessThanOrEqualTo(attendance.get("attendanceTime"), endDate));
            }

            predicates.add(builder.equal(schoolJoin.get("id"), schoolId));

            return builder.and(predicates.toArray(Predicate[]::new));
        };

        return findAll(attendanceSpecification, pageable);
    }

    @Query("""
                SELECT a
                FROM Attendance a
                WHERE a.student.id = :studentId
                ORDER BY a.attendanceTime DESC
            """)
    List<Attendance> findLastAttendanceByStudentAndSchool(
            @Param("studentId") Long studentId,
            Pageable pageable
    );

    /**
     * Estadísticas por tipo de usuario.
     * Requisito: excluir totalmente registros de asistencia de estudiantes con novedad = true.
     */
    @Query(value = """
            WITH stats_by_user_type AS (
                SELECT
                    user_types.userType AS userType,

                    CASE user_types.userType
                        WHEN 'Estudiante' THEN (
                            SELECT COUNT(*)
                            FROM students
                            WHERE school_id = :schoolId
                              AND novedad = b'0'
                        )
                        WHEN 'Acudiente' THEN (SELECT COUNT(*) FROM parent WHERE school_id = :schoolId)
                        WHEN 'Empleado' THEN (SELECT COUNT(*) FROM school_employees WHERE school_id = :schoolId)
                        WHEN 'Persona Autorizada'
                            THEN (SELECT COUNT(*) FROM authorized_persons WHERE school_id = :schoolId)
                    END AS totalUsers,

                    COUNT(CASE
                        WHEN at.description = :enterFilter
                         AND (user_types.userType <> 'Estudiante' OR st.student_record_id IS NOT NULL)
                        THEN 1
                    END) AS entryRegisters,

                    COUNT(CASE
                        WHEN at.description = :outFilter
                         AND (user_types.userType <> 'Estudiante' OR st.student_record_id IS NOT NULL)
                        THEN 1
                    END) AS exitRegisters,

                    CASE user_types.userType
                        WHEN 'Estudiante' THEN COUNT(DISTINCT CASE WHEN st.student_record_id IS NOT NULL THEN a.student_record_id END)
                        WHEN 'Acudiente' THEN COUNT(DISTINCT a.parent_id)
                        WHEN 'Empleado' THEN COUNT(DISTINCT a.employee_id)
                        WHEN 'Persona Autorizada' THEN COUNT(DISTINCT a.authorized_person_id)
                    END AS distinctUsersWithRegisters

                FROM (
                    SELECT 'Estudiante' AS userType
                    UNION ALL SELECT 'Acudiente'
                    UNION ALL SELECT 'Empleado'
                    UNION ALL SELECT 'Persona Autorizada'
                ) user_types

                LEFT JOIN attendance a
                    ON a.user_type = user_types.userType
                    AND a.school_id = :schoolId
                    AND a.attendance_time BETWEEN :initDateTime AND :endDateTime

                -- Solo valida estudiante si es userType Estudiante, y si el student existe y novedad = 0.
                LEFT JOIN students st
                    ON user_types.userType = 'Estudiante'
                   AND st.student_record_id = a.student_record_id
                   AND st.school_id = :schoolId
                   AND st.novedad = b'0'

                LEFT JOIN attendance_type at
                    ON a.attendance_type_id = at.attendance_type_id

                GROUP BY user_types.userType
            )

            SELECT
                userType,
                totalUsers,
                entryRegisters,
                exitRegisters,
                (entryRegisters + exitRegisters) AS totalRegisters,
                distinctUsersWithRegisters,

                CASE
                    WHEN totalUsers = 0 THEN 0
                    ELSE ROUND(
                        distinctUsersWithRegisters * 100.0 / totalUsers,
                        2
                    )
                END AS userUsePercentage,

                CASE
                    WHEN distinctUsersWithRegisters = 0 THEN 0
                    ELSE ROUND(
                        (entryRegisters + exitRegisters) * 1.0 / distinctUsersWithRegisters,
                        2
                    )
                END AS userAverageRegisters

            FROM stats_by_user_type

            UNION ALL

            SELECT
                'Total' AS userType,
                SUM(totalUsers),
                SUM(entryRegisters),
                SUM(exitRegisters),
                SUM(entryRegisters + exitRegisters),
                SUM(distinctUsersWithRegisters),

                CASE
                    WHEN SUM(totalUsers) = 0 THEN 0
                    ELSE ROUND(
                        SUM(distinctUsersWithRegisters) * 100.0 / SUM(totalUsers),
                        2
                    )
                END AS userUsePercentage,

                CASE
                    WHEN SUM(distinctUsersWithRegisters) = 0 THEN 0
                    ELSE ROUND(
                        (SUM(entryRegisters) + SUM(exitRegisters)) * 1.0
                            / SUM(distinctUsersWithRegisters),
                        2
                    )
                END AS userAverageRegisters

            FROM stats_by_user_type
            """,
            nativeQuery = true)
    List<UserTypeStatistics> getUserStatistics(
            @Param("schoolId") Long schoolId,
            @Param("initDateTime") LocalDateTime initDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("enterFilter") String enterFilter,
            @Param("outFilter") String outFilter
    );

    /**
     * Top estudiantes por asistencia.
     * Excluye estudiantes novedad=true (y por ende sus asistencias) totalmente.
     */
    @Query(value = """
            SELECT
                s.student_id AS studentId,

                CONCAT(s.first_name, ' ', s.last_name) AS fullName,
                s.grade_level AS grade,

                COUNT(CASE WHEN at.description = :enterFilter THEN 1 END) AS entryRegisters,
                COUNT(CASE WHEN at.description = :outFilter THEN 1 END)  AS exitRegisters,
                COUNT(
                    CASE
                        WHEN at.description IN (:enterFilter, :outFilter)
                        THEN 1
                    END
                ) AS totalRegisters,

                MAX(a.attendance_time) AS lastAttendanceTime

            FROM attendance a
                JOIN attendance_type at
                    ON a.attendance_type_id = at.attendance_type_id
                JOIN students s
                    ON s.student_record_id = a.student_record_id
                   AND s.school_id = :schoolId
                   AND s.novedad = b'0'

            WHERE a.school_id = :schoolId
              AND a.user_type = :userType
              AND a.attendance_time BETWEEN :initDateTime AND :endDateTime

            GROUP BY
                s.student_id,
                s.first_name,
                s.last_name,
                s.grade_level

            ORDER BY totalRegisters DESC
            """,
            nativeQuery = true)
    Page<TopStudentStatistics> findTopUserTypeByAttendance(
            @Param("schoolId") Long schoolId,
            @Param("initDateTime") LocalDateTime initDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("enterFilter") String enterFilter,
            @Param("outFilter") String outFilter,
            @Param("userType") String userType,
            Pageable pageable
    );

    /**
     * Uso por grado.
     * Excluye estudiantes novedad=true y su asistencia.
     */
    @Query(value = """
            WITH gradeGeneralStats AS (
                SELECT
                    s.grade_level AS grade,
                    COUNT(*) AS totalStudents
                FROM students s
                WHERE s.school_id = :schoolId
                  AND s.novedad = b'0'
                GROUP BY s.grade_level
            )

            SELECT
                g.grade AS grade,

                COUNT(
                    DISTINCT CASE
                        WHEN a.attendance_time BETWEEN :initDateTime AND :endDateTime
                         AND at.description IN (:enterFilter, :outFilter)
                        THEN a.student_record_id
                    END
                ) AS studentsWithRegisters,

                COUNT(
                    CASE
                        WHEN a.attendance_time BETWEEN :initDateTime AND :endDateTime
                         AND at.description IN (:enterFilter, :outFilter)
                        THEN 1
                    END
                ) AS totalRegisters,

                CASE
                    WHEN g.totalStudents = 0 THEN 0
                    ELSE ROUND(
                        COUNT(
                            DISTINCT CASE
                                WHEN a.attendance_time BETWEEN :initDateTime AND :endDateTime
                                THEN a.student_record_id
                            END
                        ) * 100.0 / g.totalStudents,
                        2
                    )
                END AS usagePercentage

            FROM gradeGeneralStats g
            LEFT JOIN students s
                ON s.grade_level = g.grade
               AND s.school_id = :schoolId
               AND s.novedad = b'0'
            LEFT JOIN attendance a
                ON a.student_record_id = s.student_record_id
               AND a.school_id = :schoolId
               AND a.attendance_time BETWEEN :initDateTime AND :endDateTime
               AND a.user_type = 'Estudiante'
            LEFT JOIN attendance_type at
                ON at.attendance_type_id = a.attendance_type_id
               AND at.description IN (:enterFilter, :outFilter)

            GROUP BY g.grade, g.totalStudents
            ORDER BY g.grade;
                        """,
            nativeQuery = true)
    List<GradeUsagesStatistics> getGradeUsageStatistics(
            @Param("schoolId") Long schoolId,
            @Param("initDateTime") LocalDateTime initDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("enterFilter") String enterFilter,
            @Param("outFilter") String outFilter
    );

    /**
     * Rangos de hora (desc).
     * Excluye registros de asistencia de estudiantes con novedad=true.
     */
    @Query(value = """
    WITH timeRanges AS (
        SELECT DISTINCT
            IF(
                MINUTE(a.attendance_time) < 30,
                DATE_FORMAT(a.attendance_time, '%H:00:00'),
                DATE_FORMAT(a.attendance_time, '%H:30:00')
            ) AS initTemporalRange,

            IF(
                MINUTE(a.attendance_time) < 30,
                DATE_FORMAT(a.attendance_time + INTERVAL 1 HOUR, '%H:00:00'),
                DATE_FORMAT(a.attendance_time + INTERVAL 1 HOUR, '%H:30:00')
            ) AS endTemporalRange

        FROM attendance a
        WHERE a.school_id = :schoolId
          AND a.attendance_time BETWEEN :initDateTime AND :endDateTime
    )

    SELECT
        CONCAT(tr.initTemporalRange, ' - ', tr.endTemporalRange) AS temporalRange,

        COUNT(
            CASE
                WHEN at.description IN (:enterFilter, :outFilter)
                 AND (a.user_type <> 'Estudiante' OR s.student_record_id IS NOT NULL)
                THEN 1
            END
        ) AS totalRegisters,

        COUNT(CASE WHEN a.user_type = 'Estudiante' AND s.student_record_id IS NOT NULL THEN 1 END)         AS studentRegisters,
        COUNT(CASE WHEN a.user_type = 'Acudiente' THEN 1 END)          AS parentRegisters,
        COUNT(CASE WHEN a.user_type = 'Empleado' THEN 1 END)           AS employeeRegisters,
        COUNT(CASE WHEN a.user_type = 'Persona Autorizada' THEN 1 END) AS authorizedPersonRegisters

    FROM timeRanges tr
        JOIN attendance a
            ON TIME(a.attendance_time) >= tr.initTemporalRange
           AND TIME(a.attendance_time) <  tr.endTemporalRange
        JOIN attendance_type at
            ON at.attendance_type_id = a.attendance_type_id
        LEFT JOIN students s
            ON a.user_type = 'Estudiante'
           AND s.student_record_id = a.student_record_id
           AND s.school_id = :schoolId
           AND s.novedad = b'0'

    WHERE a.school_id = :schoolId
      AND a.attendance_time BETWEEN :initDateTime AND :endDateTime
      AND at.description IN (:enterFilter, :outFilter)

    GROUP BY temporalRange
    ORDER BY totalRegisters DESC
       """,
            countQuery = """
        SELECT COUNT(DISTINCT
            IF(
                MINUTE(attendance_time) < 30,
                DATE_FORMAT(attendance_time, '%H:00:00'),
                DATE_FORMAT(attendance_time, '%H:30:00')
            )
        )
        FROM attendance a
        JOIN attendance_type at
            ON at.attendance_type_id = a.attendance_type_id
        LEFT JOIN students s
            ON a.user_type = 'Estudiante'
           AND s.student_record_id = a.student_record_id
           AND s.school_id = :schoolId
           AND s.novedad = b'0'
        WHERE a.school_id = :schoolId
          AND a.attendance_time BETWEEN :initDateTime AND :endDateTime
          AND at.description IN (:enterFilter, :outFilter)
          AND (a.user_type <> 'Estudiante' OR s.student_record_id IS NOT NULL)
    """,
            nativeQuery = true)
    Page<TimeRangeStatistics> findAttendanceByTimeRangeDesc(
            @Param("schoolId") Long schoolId,
            @Param("initDateTime") LocalDateTime initDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("enterFilter") String enterFilter,
            @Param("outFilter") String outFilter,
            Pageable pageable
    );

    /**
     * Rangos de hora (asc).
     * Excluye registros de asistencia de estudiantes con novedad=true.
     */
    @Query(value = """
    WITH timeRanges AS (
        SELECT DISTINCT
            IF(
                MINUTE(a.attendance_time) < 30,
                DATE_FORMAT(a.attendance_time, '%H:00:00'),
                DATE_FORMAT(a.attendance_time, '%H:30:00')
            ) AS initTemporalRange,

            IF(
                MINUTE(a.attendance_time) < 30,
                DATE_FORMAT(a.attendance_time + INTERVAL 1 HOUR, '%H:00:00'),
                DATE_FORMAT(a.attendance_time + INTERVAL 1 HOUR, '%H:30:00')
            ) AS endTemporalRange

        FROM attendance a
        WHERE a.school_id = :schoolId
          AND a.attendance_time BETWEEN :initDateTime AND :endDateTime
    )

    SELECT
        CONCAT(tr.initTemporalRange, ' - ', tr.endTemporalRange) AS temporalRange,

        COUNT(
            CASE
                WHEN at.description IN (:enterFilter, :outFilter)
                 AND (a.user_type <> 'Estudiante' OR s.student_record_id IS NOT NULL)
                THEN 1
            END
        ) AS totalRegisters,

        COUNT(CASE WHEN a.user_type = 'Estudiante' AND s.student_record_id IS NOT NULL THEN 1 END)         AS studentRegisters,
        COUNT(CASE WHEN a.user_type = 'Acudiente' THEN 1 END)          AS parentRegisters,
        COUNT(CASE WHEN a.user_type = 'Empleado' THEN 1 END)           AS employeeRegisters,
        COUNT(CASE WHEN a.user_type = 'Persona Autorizada' THEN 1 END) AS authorizedPersonRegisters

    FROM timeRanges tr
        JOIN attendance a
            ON TIME(a.attendance_time) >= tr.initTemporalRange
           AND TIME(a.attendance_time) <  tr.endTemporalRange
        JOIN attendance_type at
            ON at.attendance_type_id = a.attendance_type_id
        LEFT JOIN students s
            ON a.user_type = 'Estudiante'
           AND s.student_record_id = a.student_record_id
           AND s.school_id = :schoolId
           AND s.novedad = b'0'

    WHERE a.school_id = :schoolId
      AND a.attendance_time BETWEEN :initDateTime AND :endDateTime
      AND at.description IN (:enterFilter, :outFilter)

    GROUP BY temporalRange
    ORDER BY totalRegisters ASC
       """,
            countQuery = """
        SELECT COUNT(DISTINCT
            IF(
                MINUTE(attendance_time) < 30,
                DATE_FORMAT(attendance_time, '%H:00:00'),
                DATE_FORMAT(attendance_time, '%H:30:00')
            )
        )
        FROM attendance a
        JOIN attendance_type at
            ON at.attendance_type_id = a.attendance_type_id
        LEFT JOIN students s
            ON a.user_type = 'Estudiante'
           AND s.student_record_id = a.student_record_id
           AND s.school_id = :schoolId
           AND s.novedad = b'0'
        WHERE a.school_id = :schoolId
          AND a.attendance_time BETWEEN :initDateTime AND :endDateTime
          AND at.description IN (:enterFilter, :outFilter)
          AND (a.user_type <> 'Estudiante' OR s.student_record_id IS NOT NULL)
    """,
            nativeQuery = true)
    Page<TimeRangeStatistics> findAttendanceByTimeRangeAsc(
            @Param("schoolId") Long schoolId,
            @Param("initDateTime") LocalDateTime initDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("enterFilter") String enterFilter,
            @Param("outFilter") String outFilter,
            Pageable pageable
    );

    /**
     * Asistencia por día.
     * Excluye totalmente registros de estudiantes con novedad=true.
     */
    @Query(value = """
    WITH RECURSIVE days AS (
        SELECT DATE(:initDateTime) AS day
        UNION ALL
        SELECT day + INTERVAL 1 DAY
        FROM days
        WHERE day < DATE(:endDateTime)
    )

    SELECT
        d.day AS day,

        COUNT(
            CASE
                WHEN a.attendance_id IS NOT NULL
                 AND (a.user_type <> 'Estudiante' OR s.student_record_id IS NOT NULL)
                THEN 1
            END
        ) AS totalRegisters,

        COUNT(CASE WHEN a.user_type = 'Estudiante' AND s.student_record_id IS NOT NULL THEN 1 END)         AS studentRegisters,
        COUNT(CASE WHEN a.user_type = 'Empleado' THEN 1 END)           AS employeeRegisters,
        COUNT(CASE WHEN a.user_type = 'Acudiente' THEN 1 END)          AS parentRegisters,
        COUNT(CASE WHEN a.user_type = 'Persona Autorizada' THEN 1 END) AS authorizedPersonRegisters

    FROM days d
        LEFT JOIN attendance a
            ON DATE(a.attendance_time) = d.day
           AND a.school_id = :schoolId
           AND a.attendance_time BETWEEN :initDateTime AND :endDateTime
        LEFT JOIN students s
            ON a.user_type = 'Estudiante'
           AND s.student_record_id = a.student_record_id
           AND s.school_id = :schoolId
           AND s.novedad = b'0'
        LEFT JOIN attendance_type at
            ON at.attendance_type_id = a.attendance_type_id
           AND at.description IN (:enterFilter, :outFilter)

    GROUP BY d.day
    ORDER BY d.day ASC
    """,
            nativeQuery = true)
    List<DailyAttendanceStatistics> findAttendanceByDay(
            @Param("schoolId") Long schoolId,
            @Param("initDateTime") LocalDateTime initDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("enterFilter") String enterFilter,
            @Param("outFilter") String outFilter
    );
}