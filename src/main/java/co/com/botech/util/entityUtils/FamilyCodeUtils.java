package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.FamilyCode;
import co.com.botech.repository.FamilyCodeRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FamilyCodeUtils {

    private final FamilyCodeRepository familyCodeRepository;

    public FamilyCode getFamilyCode(String familyCodeString) {
        return familyCodeRepository.findByCode(familyCodeString)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el código de Familia " + familyCodeString));
    }
}
