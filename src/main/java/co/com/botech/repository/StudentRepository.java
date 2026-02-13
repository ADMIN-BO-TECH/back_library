package co.com.botech.repository;

import co.com.botech.customDto.GradeStudentsCount;
import co.com.botech.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findBySchool_Id(Long schoolId);
    List<Student> findBySchool_IdAndFamily_FamilyCode(Long schoolId, String familyCode);
    List<Student> findByFamily_Id(Long familyId);
    List<Student> findByStudentIdInAndSchool_Id(List<Long> studentIds, Long schoolId);
    Optional<Student> findByStudentIdAndSchool_Id(Long studentId, Long schoolId);
    boolean existsByStudentIdAndSchool_Id(Long studentId, Long schoolId);
    boolean existsByIdAndStudentId(Long studentRecordId, Long studentId);

    @Query("""
        SELECT DISTINCT s.family.id
        FROM Student s
        WHERE s.gradeLevel IN :gradeList
          AND s.novelty = false
    """)
    List<Long> findFamilyIdsByGrades(@Param("gradeList") List<String> gradeList);

    @Query("""
        SELECT DISTINCT s.family.id
        FROM Student s
        WHERE s.studentId IN :studentSchoolIds
          AND s.novelty = false
    """)
    List<Long> findFamilyIdsByStudentSchoolIds(@Param("studentSchoolIds") List<Long> studentSchoolIds);

    @Query("""
        SELECT DISTINCT s.gradeLevel
        FROM Student s
        WHERE s.school.id = :schoolId
          AND s.novelty = false
    """)
    List<String> findDistinctGradeLevelsBySchoolId(@Param("schoolId") Long schoolId);

    @Query(value = """
        SELECT
            s.grade_level AS grade_level,
            CAST(COUNT(*) AS UNSIGNED) AS totalStudents
        FROM students s
        WHERE s.school_id = :schoolId
          AND s.novedad = b'0'
        GROUP BY s.grade_level
        """,
            nativeQuery = true)
    List<GradeStudentsCount> countGradeLevelStatistics(@Param("schoolId") Long schoolId);
}