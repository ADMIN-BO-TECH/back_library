package co.com.botech.repository;

import co.com.botech.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query(
            "SELECT a FROM Attendance a " +

                    // ID FILTER
                    "WHERE (COALESCE(:filterIdTerms, null) IS NULL OR EXISTS (" +
                    "    SELECT t FROM java.lang.String t WHERE t IN :filterIdTerms AND (" +
                    "       str(a.id) LIKE CONCAT('%', t, '%')" +
                    "    )" +
                    ")) " +

                    // USER TYPE FILTER
                    "AND (COALESCE(:userTypeTerms, null) IS NULL OR EXISTS (" +
                    "    SELECT t FROM java.lang.String t WHERE t IN :userTypeTerms AND (" +
                    "       a.userType IS NOT NULL AND LOWER(a.userType) LIKE CONCAT('%', LOWER(t), '%')" +
                    "    )" +
                    ")) " +

                    // GLOBAL FILTER
                    "AND (COALESCE(:globalFilterTerms, null) IS NULL OR EXISTS (" +
                    "    SELECT filterValue FROM java.lang.String filterValue WHERE filterValue IN :globalFilterTerms AND (" +
                    //AUTHORIZED PERSON
                    "       (a.authorizedPerson IS NOT NULL AND LOWER(CONCAT(a.authorizedPerson.firstName, ' ', a.authorizedPerson.lastName, ' ', a.authorizedPerson.documentNumber)) LIKE CONCAT('%', LOWER(filterValue), '%')) OR " +
                    //PARENT
                    "       (a.parent IS NOT NULL AND LOWER(CONCAT(a.parent.firstName, ' ', a.parent.lastName, ' ', a.parent.documentNumber)) LIKE CONCAT('%', LOWER(filterValue), '%')) OR " +
                    //STUDENT
                    "       (a.student IS NOT NULL AND LOWER(CONCAT(a.student.firstName, ' ', a.student.lastName, ' ', a.student.studentId)) LIKE CONCAT('%', LOWER(filterValue), '%')) OR " +
                    //SCHOOL EMPLOYEE
                    "       (a.schoolEmployee IS NOT NULL AND LOWER(CONCAT(a.schoolEmployee.firstName, ' ', a.schoolEmployee.lastName, ' ', a.schoolEmployee.documentNumber)) LIKE CONCAT('%', LOWER(filterValue), '%')) " +
                    "    )" +
                    ")) " +

                    // RFID FILTER
                    "AND (COALESCE(:filterRfidRegisterTerms, null) IS NULL OR EXISTS (" +
                    "    SELECT t FROM java.lang.String t WHERE t IN :filterRfidRegisterTerms AND (" +
                    "       a.rfidRegister IS NOT NULL AND " +
                    "       LOWER(CONCAT(a.rfidRegister.description, ' ', a.rfidRegister.rfidTag, ' ', a.rfidRegister.kindDevice.description)) " +
                    "       LIKE CONCAT('%', LOWER(t), '%')" +
                    "    )" +
                    ")) " +

                    // ATTENDANCE TYPE FILTER
                    "AND (COALESCE(:typeAttendanceFilterTerms, null) IS NULL OR EXISTS (" +
                    "    SELECT t FROM java.lang.String t WHERE t IN :typeAttendanceFilterTerms AND (" +
                    "       LOWER(a.type.description) LIKE CONCAT('%', LOWER(t), '%')" +
                    "    )" +
                    ")) " +

                    // DATETIME FILTER
                    "AND (:startDate IS NULL OR a.attendanceTime >= :startDate) " +
                    "AND (:endDate IS NULL OR a.attendanceTime <= :endDate)"
    )
    Page<Attendance> getAttendanceByCustomQuery(
            @Param("filterIdTerms") List<String> filterIdTerms,
            @Param("globalFilterTerms") List<String> globalFilterTerms,
            @Param("userTypeTerms") List<String> userTypeTerms,
            @Param("filterRfidRegisterTerms") List<String> filterRfidRegisterTerms,
            @Param("typeAttendanceFilterTerms") List<String> typeAttendanceFilterTerms,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );
}

