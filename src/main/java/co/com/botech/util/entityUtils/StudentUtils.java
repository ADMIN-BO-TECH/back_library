package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.Student;
import co.com.botech.repository.StudentRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class StudentUtils {

    private final StudentRepository studentRepository;

    public Student getStudentByStudentSchoolIdAndSchoolIdPass(Long studentSchoolId, Long schoolId) {
        return studentRepository.findByStudentIdAndSchool_Id(studentSchoolId, schoolId)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se han encontrado estudiantes con el id de colegio " + studentSchoolId));
    }

    public Student getStudentByStudentSchoolIdAndSchoolIdAttendanceSocket(String document, Long schoolId, String details, String code, int option) {
        String detailsValue = (details == null || details.isBlank()) ? "" : details;

        return (option == 1
                ? studentRepository.findByStudentIdAndSchool_Id(Long.valueOf(document), schoolId)
                : studentRepository.findByRfidTagAndSchool_Id(document, schoolId))
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        code + ". No se han encontrado estudiantes con el " +
                                (option == 1 ? "id de colegio " : "rfid ") + document +
                                (detailsValue.isEmpty() ? "" : " - " + detailsValue)));
    }
}
