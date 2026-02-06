package co.com.botech.repository;

import co.com.botech.customDto.GradeStudentsCount;
import co.com.botech.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findBySchool_IdAndFamilyCode_Code(Long schoolId, String familyCode);
    List<Student> findBySchool_Id(Long schoolId);
    List<Student> findByStudentIdInAndSchool_Id(List<Long> studentIds, Long schoolId);

    Optional<Student> findById(Long id);

    Optional<Student> findByStudentIdAndSchool_Id(Long studentId, Long schoolId);

    boolean existsByStudentIdAndSchool_Id(Long studentId, Long schoolId);

    boolean existsByIdAndStudentId(Long studentRecordId, Long studentId);

    @Query("SELECT DISTINCT s.family.id FROM Student s WHERE s.gradeLevel IN :gradeList")
    List<Long> findFamilyCodesByGrades(@Param("gradeList") List<String> gradeList);

    @Query("SELECT DISTINCT s.family.id FROM Student s WHERE s.studentId IN :studentSchoolIds")
    List<Long> findFamilyCodesByStudentSchoolIds(@Param("studentSchoolIds") List<Long> studentSchoolIds);

    @Query("SELECT DISTINCT s.gradeLevel FROM Student s WHERE s.school.id = :schoolId")
    List<String> findDistinctGradeLevelsBySchoolId(@Param("schoolId") Long schoolId);

    List<Student> findByFamilyCode_Id(Long familyCodeId);

    @Query(value = "SELECT DISTINCT s.grade_level, CAST(COUNT(*) AS UNSIGNED) AS totalStudents FROM students s WHERE s.school_id = :schoolId", nativeQuery = true)
    List<GradeStudentsCount> countGradeLevelStatistics(@Param("schoolId") Long schoolId);


}
