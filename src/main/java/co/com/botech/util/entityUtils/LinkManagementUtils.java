package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.KindDevice;
import co.com.botech.entity.LinkManagement;
import co.com.botech.repository.KindDeviceRepository;
import co.com.botech.repository.LinkManagementRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LinkManagementUtils {
    private final LinkManagementRepository linkManagementRepository;

    public LinkManagement getLinkManagement(Long linkId) {
        return linkManagementRepository.findById(linkId)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el Link con ID: " + linkId));
    }
}
