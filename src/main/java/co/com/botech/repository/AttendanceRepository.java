package co.com.botech.repository;

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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {

    default Page<Attendance> getAttendanceByCustomQuery(
            List<String> filterIdTerms,
            List<String> globalFilterTerms,
            List<String> userTypeTerms,
            List<String> filterRfidRegisterTerms,
            List<String> typeAttendanceFilterTerms,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    ) {

        Specification<Attendance> spec = (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // LEFT JOINS según tu entidad
            Join<Attendance, AuthorizedPerson> authJoin =
                    root.join("authorizedPerson", JoinType.LEFT);

            Join<Attendance, Parent> parentJoin =
                    root.join("parent", JoinType.LEFT);

            Join<Attendance, Student> studentJoin =
                    root.join("student", JoinType.LEFT);

            Join<Attendance, SchoolEmployee> employeeJoin =
                    root.join("schoolEmployee", JoinType.LEFT);

            Join<Attendance, RfidRegister> rfidJoin =
                    root.join("rfidRegister", JoinType.LEFT);

            Join<RfidRegister, KindDevice> kindJoin =
                    rfidJoin.join("kindDevice", JoinType.LEFT);

            /*
             * ID filter
             */
            if (filterIdTerms != null && !filterIdTerms.isEmpty()) {
                predicates.add(
                        builder.or(
                                filterIdTerms.stream()
                                        .map(term -> builder.like(
                                                root.get("id").as(String.class),
                                                "%" + term + "%"
                                        ))
                                        .toArray(Predicate[]::new)
                        )
                );
            }

            /*
             * User type filter
             */
            if (userTypeTerms != null && !userTypeTerms.isEmpty()) {
                predicates.add(
                        builder.or(
                                userTypeTerms.stream()
                                        .map(term -> builder.like(
                                                builder.lower(root.get("userType")),
                                                "%" + term.toLowerCase() + "%"
                                        ))
                                        .toArray(Predicate[]::new)
                        )
                );
            }

            /*
             * Global filter
             */
            if (globalFilterTerms != null && !globalFilterTerms.isEmpty()) {

                List<Predicate> globalPreds = new ArrayList<>();

                for (String t : globalFilterTerms) {
                    String like = "%" + t.toLowerCase() + "%";

                    Predicate p = builder.or(
                            // authorized_person
                            builder.like(builder.lower(authJoin.get("firstName")), like),
                            builder.like(builder.lower(authJoin.get("lastName")), like),
                            builder.like(builder.lower(authJoin.get("documentNumber")), like),

                            // parent
                            builder.like(builder.lower(parentJoin.get("firstName")), like),
                            builder.like(builder.lower(parentJoin.get("lastName")), like),
                            builder.like(builder.lower(parentJoin.get("documentNumber")), like),

                            // student
                            builder.like(builder.lower(studentJoin.get("firstName")), like),
                            builder.like(builder.lower(studentJoin.get("lastName")), like),
                            builder.like(studentJoin.get("studentId").as(String.class), like),

                            // school employee
                            builder.like(builder.lower(employeeJoin.get("firstName")), like),
                            builder.like(builder.lower(employeeJoin.get("lastName")), like),
                            builder.like(builder.lower(employeeJoin.get("documentNumber")), like)
                    );

                    globalPreds.add(p);
                }

                predicates.add(builder.or(globalPreds.toArray(Predicate[]::new)));
            }

            /*
             * RFID filter
             */
            if (filterRfidRegisterTerms != null && !filterRfidRegisterTerms.isEmpty()) {

                List<Predicate> rfidPreds = new ArrayList<>();

                for (String t : filterRfidRegisterTerms) {
                    String like = "%" + t.toLowerCase() + "%";

                    Expression<String> concat = builder.lower(
                            builder.concat(
                                    builder.coalesce(rfidJoin.get("description"), ""),
                                    builder.concat(" ",
                                            builder.concat(
                                                    builder.coalesce(rfidJoin.get("rfidTag"), ""),
                                                    builder.concat(" ",
                                                            builder.coalesce(kindJoin.get("description"), "")
                                                    )
                                            )
                                    )
                            )
                    );

                    rfidPreds.add(builder.like(concat, like));
                }

                predicates.add(builder.or(rfidPreds.toArray(Predicate[]::new)));
            }

            /*
             * Type filter
             */
            if (typeAttendanceFilterTerms != null && !typeAttendanceFilterTerms.isEmpty()) {
                predicates.add(
                        builder.or(
                                typeAttendanceFilterTerms.stream()
                                        .map(term -> builder.like(
                                                builder.lower(root.get("type").get("description")),
                                                "%" + term.toLowerCase() + "%"
                                        ))
                                        .toArray(Predicate[]::new)
                        )
                );
            }

            /*
             * Date filters
             */
            if (startDate != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("attendanceTime"), startDate));
            }

            if (endDate != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("attendanceTime"), endDate));
            }

            return builder.and(predicates.toArray(Predicate[]::new));
        };

        return findAll(spec, pageable);
    }

}