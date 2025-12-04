package co.com.botech.repository;

import co.com.botech.entity.Attendance;
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
        Specification<Attendance> spec = (attendance, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            //  ID FILTER
            if (filterIdTerms != null && !filterIdTerms.isEmpty()) {
                List<Predicate> idPredicates = new ArrayList<>();
                for (String term : filterIdTerms) {
                    idPredicates.add(builder.like(attendance.get("id").as(String.class), "%" + term + "%"));
                }
                predicates.add(builder.or(idPredicates.toArray(new Predicate[0])));

            }

            // USER TYPE FILTER
            if (userTypeTerms != null && !userTypeTerms.isEmpty()) {
                List<Predicate> typePredicates = new ArrayList<>();
                for (String term : userTypeTerms) {
                    typePredicates.add(builder.like(builder.lower(attendance.get("userType")), "%" + term.toLowerCase() + "%"));
                }
                predicates.add(builder.or(typePredicates.toArray(new Predicate[0])));
            }

            // GLOBAL FILTER
            if (globalFilterTerms != null && !globalFilterTerms.isEmpty()) {
                List<Predicate> generalPredicates = new ArrayList<>();

                for (String term : globalFilterTerms) {
                    String variable = "%" + term.toLowerCase() + "%";


                    // Authorized Person
                    Predicate authPerson = builder.like(builder.lower(builder.concat(builder.concat(builder.concat(
                                            builder.coalesce(attendance.get("authorizedPerson").get("firstName"), ""), " "),
                                    builder.coalesce(attendance.get("authorizedPerson").get("lastName"), "")),
                            builder.coalesce(attendance.get("authorizedPerson").get("documentNumber"), ""))), variable);

                    // Parent
                    Predicate parent = builder.like(builder.lower(builder.concat(builder.concat(builder.concat(
                                            builder.coalesce(attendance.get("parent").get("firstName"), ""), " "),
                                    builder.coalesce(attendance.get("parent").get("lastName"), "")),
                            builder.coalesce(attendance.get("parent").get("documentNumber"), ""))), variable);

                    // Student
                    Predicate student = builder.like(builder.lower(builder.concat(builder.concat(builder.concat(
                                            builder.coalesce(attendance.get("student").get("firstName"), ""), " "),
                                    builder.coalesce(attendance.get("student").get("lastName"), "")),
                            builder.coalesce(attendance.get("student").get("studentId").as(String.class), ""))), variable);

                    // Employee
                    Predicate employee = builder.like(builder.lower(builder.concat(builder.concat(builder.concat(
                                            builder.coalesce(attendance.get("schoolEmployee").get("firstName"), ""), " "),
                                    builder.coalesce(attendance.get("schoolEmployee").get("lastName"), "")),
                            builder.coalesce(attendance.get("schoolEmployee").get("documentNumber"), ""))), variable);

                    generalPredicates.add(builder.or(authPerson, parent, student, employee));
                }
                predicates.add(builder.or(generalPredicates.toArray(new Predicate[0])));
            }

            //  RFID FILTER
            if (filterRfidRegisterTerms != null && !filterRfidRegisterTerms.isEmpty()) {
                List<Predicate> rfidPredicates = new ArrayList<>();
                for (String term : filterRfidRegisterTerms) {
                    // Concatenar campos RFID
                    Predicate rfidMatch = builder.like(builder.lower(builder.concat(builder.concat(builder.concat(
                                                    builder.coalesce(attendance.get("rfidRegister").get("description"), ""), " "),
                                            builder.coalesce(attendance.get("rfidRegister").get("rfidTag"), "")),
                                    builder.coalesce(attendance.get("rfidRegister").get("kindDevice").get("description"), ""))),
                            "%" + term.toLowerCase() + "%");
                    rfidPredicates.add(rfidMatch);
                }
                predicates.add(builder.or(rfidPredicates.toArray(new Predicate[0])));
            }

            // ATTENDANCE TYPE FILTER
            if (typeAttendanceFilterTerms != null && !typeAttendanceFilterTerms.isEmpty()) {
                List<Predicate> attTypePredicates = new ArrayList<>();
                for (String term : typeAttendanceFilterTerms) {
                    attTypePredicates.add(builder.like(builder.lower(attendance.get("type").get("description")), "%" + term.toLowerCase() + "%"));
                }
                predicates.add(builder.or(attTypePredicates.toArray(new Predicate[0])));
            }

            // DATE FILTERS
            if (startDate != null) {
                predicates.add(builder.greaterThanOrEqualTo(attendance.get("attendanceTime"), startDate));
            }
            if (endDate != null) {
                predicates.add(builder.lessThanOrEqualTo(attendance.get("attendanceTime"), endDate));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };

        return findAll(spec, pageable);
    }
}