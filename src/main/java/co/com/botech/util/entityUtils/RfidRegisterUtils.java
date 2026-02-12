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
                .orElseThrow(() -> new CustomException(
                        CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el registro RFID con ID " + rfidRegisterId
                ));
    }
    public RfidRegister getVehicleRfidRegisterByTag(String rfidTag) {
        if (rfidTag == null || rfidTag.isBlank()) {
            throw new CustomException(
                    CustomExceptionCodeConstants.INVALID_REQUEST,
                    "El rfidTag es obligatorio"
            );
        }

        String normalized = rfidTag.trim();

        return rfidRegisterRepository.findByRfidTagIgnoreCase(normalized)
                .orElseThrow(() -> new CustomException(
                        CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "El rfidTag '" + normalized + "' no está registrado en la base de datos"
                ));
    }

    private String safeTrim(String s) {
        return s == null ? null : s.trim();
    }
}
