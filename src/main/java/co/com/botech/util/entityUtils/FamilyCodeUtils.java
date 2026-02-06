package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.Family;
import co.com.botech.repository.FamilyRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FamilyCodeUtils {

    private final FamilyRepository familyRepository;

    public Family getFamilyCode(String familyCodeString) {
        return familyRepository.findByFamilyCode(familyCodeString)
                .orElseThrow(() -> new CustomException(
                        CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el código de Familia " + familyCodeString
                ));
    }
}