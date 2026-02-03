package co.com.botech.repository;

import co.com.botech.customDto.StopInformationByRoute;
import co.com.botech.entity.StopInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StopInformationRepository extends JpaRepository<StopInformation, Long> {
    @Query("""
                SELECT si
                FROM StopInformation si
                JOIN si.stop s
                JOIN s.route r
                WHERE si.student.id = :idStudent
                  AND FUNCTION('TIME', FUNCTION('STR_TO_DATE', r.startTime, '%l:%i %p'))
                        <= FUNCTION('TIME', FUNCTION('STR_TO_DATE', :hour, '%l:%i %p'))
                  AND FUNCTION('TIME', FUNCTION('STR_TO_DATE', r.endTime, '%l:%i %p'))
                        >= FUNCTION('TIME', FUNCTION('STR_TO_DATE', :hour, '%l:%i %p'))
                  AND si.state = :state
                ORDER BY s.stopOrder asc

            """)
    List<StopInformation> findActiveStopsOfStudentInSchedule(
            @Param("idStudent") Long idStudent,
            @Param("hour") String hour,
            @Param("state") String state);


    @Query("""
                SELECT si
                FROM StopInformation si
                JOIN si.stop s
                WHERE si.student.id = :idStudent
                  AND s.route.id = :idRoute
                  AND si.state = :state
                ORDER BY s.stopOrder asc
            """)
    List<StopInformation> findActiveStopsByStudentInAndRoute(
            @Param("idStudent") Long idStudent,
            @Param("idRoute") Long idRoute,
            @Param("state") String state);

    @Modifying(clearAutomatically = true)
    @Query("""
                UPDATE StopInformation AS si
                SET si.state = :finalStatus
                WHERE si.state = :searchedStatus
            """)
    int updateByStatus(@Param("searchedStatus") String searchedStatus,
                       @Param("finalStatus") String finalStatus);

    @Modifying(clearAutomatically = true)
    @Query("""
             DELETE FROM StopInformation si
             WHERE si.state = :searchedStatus
            """)
    int deleteByStatus(@Param("searchedStatus") String searchedStatus);

    @Modifying(clearAutomatically = true)
    @Query("""
                DELETE FROM StopInformation si
                WHERE si.stop.id = :stopId
                  AND si.student.id IN :studentIds
            """)
    int deleteByStudentIdsAndStopId(@Param("stopId") Long stopId,
                                    @Param("studentIds") List<Long> studentIds);

    @Modifying(clearAutomatically = true)
    @Query("""
                DELETE FROM StopInformation si
                WHERE si.stop.id = :stopId
                  AND si.schoolEmployee.id IN :employeeIds
            """)
    int deleteByEmployeeIdsAndStopId(@Param("stopId") Long stopId,
                                     @Param("employeeIds") List<Long> employeeIds);

    @Query("""
                SELECT si
                FROM StopInformation si
                JOIN si.stop s
                WHERE s.route.id = :idRoute
                AND si.state = :status
                ORDER BY s.stopOrder asc
            """)
    List<StopInformation> findStopInformationByStatusAndRoute(
            @Param("idRoute") Long idRoute,
            @Param("status") String status
    );


    @Query(value = """
            SELECT
            st.id AS relation_id,
                CASE
                    WHEN st.student_record_id IS NOT NULL THEN 'Estudiante'
                    WHEN st.school_employee_id IS NOT NULL THEN 'Empleado'
                    END AS person_type,

                CASE
                    WHEN st.student_record_id IS NOT NULL THEN st.student_record_id
                    WHEN st.school_employee_id IS NOT NULL THEN st.school_employee_id
                    END AS person_id,

                CASE
                    WHEN st.student_record_id IS NOT NULL THEN CONCAT(stu.first_name,' ',stu.last_name)
                    WHEN st.school_employee_id IS NOT NULL THEN CONCAT(emp.first_name,' ',emp.last_name)
                    END AS person_name,

                st.stop_id
            FROM stop_information st
                     JOIN stop s
                          ON st.stop_id = s.stop_id

                     LEFT JOIN students stu
                               ON st.student_record_id = stu.student_record_id

                     LEFT JOIN school_employees emp
                               ON st.school_employee_id = emp.employee_id

            WHERE s.route_id = :routeId;
                        """, nativeQuery = true)
    List<StopInformationByRoute> findStopRelationsByRoute(@Param("routeId") Long routeId);

    @Modifying
    @Transactional
    @Query(value = """
                INSERT INTO stop_information (stop_id, student_record_id, state)
                SELECT :stopId, s.student_record_id, :defaultStatus 
                FROM students s
                WHERE s.student_record_id IN (:studentIds)
            """, nativeQuery = true)
    int insertStudents(
            @Param("stopId") Long stopId,
            @Param("studentIds") List<Long> studentIds,
            @Param("defaultStatus") String defaultStatus
    );

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO stop_information (stop_id, school_employee_id, state)
            SELECT :stopId, e.employee_id, :defaultStatus
            FROM school_employees e
            WHERE e.employee_id IN (:employeeIds)
                        """, nativeQuery = true)
    int insertEmployees(
            @Param("stopId") Long stopId,
            @Param("employeeIds") List<Long> employeeIds,
            @Param("defaultStatus") String defaultStatus
    );

    @Modifying
    @Query("""
                DELETE FROM StopInformation si
                WHERE si.stop.id IN :stopIds
            """)
    int deleteByStopIds(@Param("stopIds") List<Long> stopIds);

}