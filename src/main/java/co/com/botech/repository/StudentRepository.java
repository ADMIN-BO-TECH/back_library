package co.com.botech.repository;

import co.com.botech.customDto.GradeStudentsCount;
import co.com.botech.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.school.id = :schoolId AND s.active = true")
    List<Student> findBySchool_Id(@Param("schoolId") Long schoolId);

    Optional<Student> findByRfidTagAndSchool_Id(String rfidTag, Long schoolId);

    @Query("SELECT s FROM Student s WHERE s.school.id = :schoolId AND s.family.familyCode = :familyCode AND s.active = true")
    List<Student> findBySchool_IdAndFamily_FamilyCode(@Param("schoolId") Long schoolId, @Param("familyCode") String familyCode);

    @Query("SELECT s FROM Student s WHERE s.family.id = :familyId AND s.active = true")
    List<Student> findByFamily_Id(@Param("familyId") Long familyId);

    @Query("SELECT s FROM Student s WHERE s.studentId IN :studentIds AND s.school.id = :schoolId AND s.active = true")
    List<Student> findByStudentIdInAndSchool_Id(@Param("studentIds") List<Long> studentIds, @Param("schoolId") Long schoolId);

    Optional<Student> findByStudentIdAndSchool_Id(Long studentId, Long schoolId);

    boolean existsByStudentIdAndSchool_Id(Long studentId, Long schoolId);

    boolean existsByIdAndStudentId(Long studentRecordId, Long studentId);

    @Query("""
        SELECT DISTINCT s.family.id
        FROM Student s
        WHERE s.gradeLevel IN :gradeList
          AND s.novelty = false
          AND s.active = true
    """)
    List<Long> findFamilyIdsByGrades(@Param("gradeList") List<String> gradeList);

    @Query("""
        SELECT DISTINCT s.family.id
        FROM Student s
        WHERE s.studentId IN :studentSchoolIds
          AND s.novelty = false
          AND s.active = true
    """)
    List<Long> findFamilyIdsByStudentSchoolIds(@Param("studentSchoolIds") List<Long> studentSchoolIds);

    @Query("""
        SELECT DISTINCT s.gradeLevel
        FROM Student s
        WHERE s.school.id = :schoolId
          AND s.novelty = false
          AND s.active = true
    """)
    List<String> findDistinctGradeLevelsBySchoolId(@Param("schoolId") Long schoolId);

    @Query(value = """
        SELECT
            s.grade_level AS grade_level,
            CAST(COUNT(*) AS UNSIGNED) AS totalStudents
        FROM students s
        WHERE s.school_id = :schoolId
          AND s.novedad = b'0'
          AND s.active = 1
        GROUP BY s.grade_level
        """,
            nativeQuery = true)
    List<GradeStudentsCount> countGradeLevelStatistics(@Param("schoolId") Long schoolId);

    @Query("SELECT s FROM Student s WHERE s.school.id = :schoolId AND s.family.familyCode IN :familyCodes AND s.active = true")
    List<Student> findBySchool_IdAndFamily_FamilyCodeIn(@Param("schoolId") Long schoolId, @Param("familyCodes") List<String> familyCodes);
}
