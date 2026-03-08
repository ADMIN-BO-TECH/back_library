package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.repository.UserFamilyRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class UserFamilyUtils {

    private final UserFamilyRepository userFamilyRepository;
    public List<String> getFamilyCodesByFirebaseUidAndSchoolIdPass(String firebaseUid, Long schoolId) {

        if (firebaseUid == null || firebaseUid.isBlank()) {
            throw new CustomException(CustomExceptionCodeConstants.BAD_REQUEST,
                    "firebaseUid es requerido");
        }
        if (schoolId == null) {
            throw new CustomException(CustomExceptionCodeConstants.BAD_REQUEST,
                    "schoolId es requerido");
        }

        List<String> codes = userFamilyRepository.findFamilyCodesByFirebaseUidAndSchoolId(firebaseUid.trim(), schoolId)
                .stream()
                .map(this::clean)
                .filter(v -> v != null)
                .distinct()
                .toList();

        if (codes.isEmpty()) {
            throw new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                    "No se encontraron familyCodes asociados al usuario");
        }

        return codes;
    }

    private String clean(String v) {
        if (v == null) return null;
        String t = v.trim();
        return t.isEmpty() ? null : t;
    }
}