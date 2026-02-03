package co.com.botech.util.entityUtils;


import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.DocumentType;
import co.com.botech.entity.StopType;
import co.com.botech.repository.DocumentTypeRepository;
import co.com.botech.repository.StopTypeRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StopTypeUtils {

    private final StopTypeRepository stopTypeRepository;

    public StopType getStopType(String stopTypeName) {
        return stopTypeRepository.getStopTypeByStopTypeName(stopTypeName)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el tipo de parada " + stopTypeName));
    }

}
