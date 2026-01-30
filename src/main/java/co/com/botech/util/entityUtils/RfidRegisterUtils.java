package co.com.botech.util.entityUtils;


import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.RfidRegister;
import co.com.botech.repository.RfidRegisterRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RfidRegisterUtils {

    private final RfidRegisterRepository rfidRegisterRepository;

    public RfidRegister getRfidRegister(Long rfidRegisterId) {
        return rfidRegisterRepository.findById(rfidRegisterId)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el registro rfid con ID" + rfidRegisterId));
    }
}
