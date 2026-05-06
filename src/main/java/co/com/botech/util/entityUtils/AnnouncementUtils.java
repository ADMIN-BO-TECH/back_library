package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.Announcement;
import co.com.botech.repository.AnnouncementRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnnouncementUtils {

    private final AnnouncementRepository announcementRepository;

    public Announcement getAnnouncementById(Long id) {
        return announcementRepository.findById(id)
                .orElseThrow(() ->
                        new CustomException(
                                CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                                "No se encontró el comunicado con id: " + id
                        )
                );
    }

    public void validateAnnouncementExists(Long id) {
        if (!announcementRepository.existsById(id)) {
            throw new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                    "No se encontró el comunicado con id: " + id);
        }
    }
}