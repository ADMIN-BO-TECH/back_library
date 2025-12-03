package co.com.botech.repository;

import co.com.botech.entity.Attendance;
import com.google.api.gax.paging.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query(
            "SELECT a FROM Attendance a " +
                    "WHERE str(a.id) LIKE CONCAT('%', :idFilter, '%') " +

                    "AND (:filterAuthorizedPerson IS NULL OR (a.authorizedPerson IS NOT NULL AND " +
                    "LOWER(CONCAT(a.authorizedPerson.firstName, ' ', a.authorizedPerson.lastName, ' ', a.authorizedPerson.documentNumber)) " +
                    "LIKE CONCAT('%', LOWER(:filterAuthorizedPerson), '%'))) " +

                    "AND (:filterParent IS NULL OR (a.parent IS NOT NULL AND " +
                    "LOWER(CONCAT(a.parent.firstName, ' ', a.parent.lastName, ' ', a.parent.documentNumber)) " +
                    "LIKE CONCAT('%', LOWER(:filterParent), '%'))) " +

                    "AND (:filterStudent IS NULL OR (a.student IS NOT NULL AND " +
                    "LOWER(CONCAT(a.student.firstName, ' ', a.student.lastName, ' ', a.student.studentId)) " +
                    "LIKE CONCAT('%', LOWER(:filterStudent), '%'))) " +

                    "AND (:filterEmployee IS NULL OR (a.schoolEmployee IS NOT NULL AND " +
                    "LOWER(CONCAT(a.schoolEmployee.firstName, ' ', a.schoolEmployee.lastName, ' ', a.schoolEmployee.documentNumber)) " +
                    "LIKE CONCAT('%', LOWER(:filterEmployee), '%'))) " +

                    "AND (:filterRfidRegister IS NULL OR (a.rfidRegister IS NOT NULL AND " +
                    "LOWER(CONCAT(a.rfidRegister.description, ' ', a.rfidRegister.rfidTag, ' ', a.rfidRegister.kindDevice.description)) " +
                    "LIKE CONCAT('%', LOWER(:filterRfidRegister), '%'))) " +

                    "AND (:typeAttendanceFilter IS NULL OR " +
                    "LOWER(a.type.description) LIKE CONCAT('%', LOWER(:typeAttendanceFilter), '%')) " +

                    "AND (:startDate IS NULL OR a.attendanceTime >= :startDate) " +
                    "AND (:endDate IS NULL OR a.attendanceTime <= :endDate)"
    )
    Page<Attendance> getAttendanceByCustomQuery(
            @Param("idFilter") String filterId,
            @Param("filterAuthorizedPerson") String filterAuthorizedPerson,
            @Param("filterParent") String filterParent,
            @Param("filterStudent") String filterStudent,
            @Param("filterEmployee") String filterEmployee,
            @Param("filterRfidRegister") String filterRfidRegister,
            @Param("typeAttendanceFilter") String typeAttendanceFilter,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );


    @Query(
            "SELECT COUNT(a) FROM Attendance a " +
                    "WHERE str(a.id) LIKE CONCAT('%', :idFilter, '%') " +

                    "AND (:filterAuthorizedPerson IS NULL OR (a.authorizedPerson IS NOT NULL AND " +
                    "LOWER(CONCAT(a.authorizedPerson.firstName, ' ', a.authorizedPerson.lastName, ' ', a.authorizedPerson.documentNumber)) " +
                    "LIKE CONCAT('%', LOWER(:filterAuthorizedPerson), '%'))) " +

                    "AND (:filterParent IS NULL OR (a.parent IS NOT NULL AND " +
                    "LOWER(CONCAT(a.parent.firstName, ' ', a.parent.lastName, ' ', a.parent.documentNumber)) " +
                    "LIKE CONCAT('%', LOWER(:filterParent), '%'))) " +

                    "AND (:filterStudent IS NULL OR (a.student IS NOT NULL AND " +
                    "LOWER(CONCAT(a.student.firstName, ' ', a.student.lastName, ' ', a.student.studentId)) " +
                    "LIKE CONCAT('%', LOWER(:filterStudent), '%'))) " +

                    "AND (:filterEmployee IS NULL OR (a.schoolEmployee IS NOT NULL AND " +
                    "LOWER(CONCAT(a.schoolEmployee.firstName, ' ', a.schoolEmployee.lastName, ' ', a.schoolEmployee.documentNumber)) " +
                    "LIKE CONCAT('%', LOWER(:filterEmployee), '%'))) " +

                    "AND (:filterRfidRegister IS NULL OR (a.rfidRegister IS NOT NULL AND " +
                    "LOWER(CONCAT(a.rfidRegister.description, ' ', a.rfidRegister.rfidTag, ' ', a.rfidRegister.kindDevice.description)) " +
                    "LIKE CONCAT('%', LOWER(:filterRfidRegister), '%'))) " +

                    "AND (:typeAttendanceFilter IS NULL OR " +
                    "LOWER(a.type.description) LIKE CONCAT('%', LOWER(:typeAttendanceFilter), '%')) " +

                    "AND (:startDate IS NULL OR a.attendanceTime >= :startDate) " +
                    "AND (:endDate IS NULL OR a.attendanceTime <= :endDate)"
    )
    Long countAttendanceByCustomQuery(
            @Param("idFilter") String filterId,
            @Param("filterAuthorizedPerson") String filterAuthorizedPerson,
            @Param("filterParent") String filterParent,
            @Param("filterStudent") String filterStudent,
            @Param("filterEmployee") String filterEmployee,
            @Param("filterRfidRegister") String filterRfidRegister,
            @Param("typeAttendanceFilter") String typeAttendanceFilter,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

}

