package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.KindDevice;
import co.com.botech.repository.KindDeviceRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KindDeviceUtils {
    private final KindDeviceRepository kindDeviceRepository;

    public KindDevice getKindDevice(String kindDeviceName) {
        return kindDeviceRepository.findByDescription(kindDeviceName)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el Tipo de Dispositivo: " + kindDeviceName));
    }
}
