package co.com.botech.repository;

import co.com.botech.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    /**
     * Buscar estudiantes por school_id
     */
    List<Student> findBySchool_Id(Long schoolId);

    Optional<Student> findById(Long id);

    boolean existsByStudentIdAndSchool_Id(Long studentId, Long schoolId);
    boolean existsByIdAndStudentId(Long studentId, Long studentRecordId);

    @Query("SELECT DISTINCT s.gradeLevel FROM Student s WHERE s.school.id = :schoolId")
    List<String> findDistinctGradeLevelsBySchoolId(@Param("schoolId") Long schoolId);

}
