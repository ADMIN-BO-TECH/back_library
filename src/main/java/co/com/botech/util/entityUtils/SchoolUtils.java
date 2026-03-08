package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.School;
import co.com.botech.repository.SchoolRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SchoolUtils {
    private final SchoolRepository schoolRepository;

    public School getSchoolPass(String schoolName) {
        return schoolRepository.findByName(schoolName)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el colegio " + schoolName));
    }

    public School getSchoolAttendanceSocket(String schoolName, String details, String code) {
        String detailsValue = (details == null || details.isBlank()) ? "" : details;
        return schoolRepository.findByName(schoolName)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        code + "No se ha encontrado el colegio " + schoolName + (detailsValue.isEmpty() ? "" : " - " + detailsValue)));
    }
}
