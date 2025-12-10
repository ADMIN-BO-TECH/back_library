package co.com.botech.repository;

import co.com.botech.customDto.UserTypeStatistics;
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


    @Query(value = """

            SELECT
                      -- CREA COLUMNA 'userType'
                      user_types.userType AS userType,
                      -- ASIGNA VALOR DE 'totalUsers' DEPENDIENDO EL VALOR
                      CASE user_types.userType
                          WHEN 'Estudiante' THEN (SELECT COUNT(*) FROM students s WHERE s.school_id = :schoolId)
                          WHEN 'Acudiente' THEN (SELECT COUNT(*) FROM parent p WHERE p.school_id = :schoolId)
                          WHEN 'Empleado' THEN (SELECT COUNT(*) FROM school_employees e WHERE e.school_id = :schoolId)
                          WHEN 'Persona Autorizada' THEN (SELECT COUNT(*) FROM authorized_persons ap WHERE ap.school_id = :schoolId)
                          ELSE 0
                      END AS totalUsers,
                      -- CONDICION PARA RECUPERAR 'entryRegisters' y 'exitRegisters'
                      CAST(COUNT(CASE WHEN at.description = :enterFilter THEN 1 END) AS UNSIGNED) AS entryRegisters,
                      CAST(COUNT(CASE WHEN at.description = :outFilter THEN 1 END) AS UNSIGNED)  AS exitRegisters
                  FROM (
                           -- SE CREA TABLA VIRTUAL PARA EVITAR VALORES NULOS EN ASISTENCIA
                           SELECT 'Estudiante' AS userType
                           UNION ALL
                           SELECT 'Acudiente'
                           UNION ALL
                           SELECT 'Empleado'
                           UNION ALL
                           SELECT 'Persona Autorizada'
                       ) AS user_types
                           LEFT JOIN attendance a
                                     ON a.user_type = user_types.userType
                                         AND a.school_id = :schoolId
                                         AND a.attendance_time BETWEEN :initDateTime AND :endDateTime
                           LEFT JOIN attendance_type at
                                     ON a.attendance_type_id = at.attendance_type_id
                  GROUP BY user_types.userType
                  
                  UNION ALL
                  
                  -- SE CREA LA COLUMNA 'Total' PARA REGISTROS GENERALES
                  SELECT 'Total' AS userType,
                         (
                             (SELECT COUNT(*) FROM students s WHERE s.school_id = :schoolId) +
                             (SELECT COUNT(*) FROM parent p WHERE p.school_id = :schoolId) +
                             (SELECT COUNT(*) FROM school_employees e WHERE e.school_id = :schoolId) +
                             (SELECT COUNT(*) FROM authorized_persons ap WHERE ap.school_id = :schoolId)
                         ) AS totalUsers,
                         CAST(SUM(totals.entryRegisters) AS UNSIGNED) AS entryRegisters,
                         CAST(SUM(totals.exitRegisters) AS UNSIGNED)  AS exitRegisters
                  FROM (
                           SELECT
                               CAST(COUNT(CASE WHEN at.description = :enterFilter THEN 1 END) AS UNSIGNED) AS entryRegisters,
                               CAST(COUNT(CASE WHEN at.description = :outFilter THEN 1 END) AS UNSIGNED)  AS exitRegisters
                           FROM attendance a
                                    JOIN attendance_type at ON a.attendance_type_id = at.attendance_type_id
                           WHERE a.school_id = :schoolId
                             AND a.attendance_time BETWEEN :initDateTime AND :endDateTime
                       ) AS totals;
                  """,
            nativeQuery = true)
    List<UserTypeStatistics> getUserStatistics(
            @Param("schoolId") Long schoolId,
            @Param("initDateTime") LocalDateTime initDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("enterFilter") String enterFilter,
            @Param("outFilter") String outFilter
    );

}