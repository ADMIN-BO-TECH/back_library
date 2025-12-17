package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.AttendanceType;
import co.com.botech.repository.AttendanceTypeRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AttendanceTypeUtils {
    private final AttendanceTypeRepository attendanceTypeRepository;

    public AttendanceType getAttendanceTypePass(String description) {
        return attendanceTypeRepository.findByDescription(description)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el tipo de acceso " + description));
    }

    public AttendanceType getAttendanceTypeAttendanceSocket(String description, String details, String code) {
        String detailsValue = (details == null || details.isBlank()) ? "" : details;
        return attendanceTypeRepository.findByDescription(description)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        code + ",No se ha encontrado el tipo de acceso " + description
                                + (detailsValue.isEmpty() ? "" : " - " + detailsValue)));
    }
}

