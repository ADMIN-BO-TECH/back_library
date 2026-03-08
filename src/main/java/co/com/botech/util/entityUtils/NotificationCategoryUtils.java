package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.NotificationCategory;
import co.com.botech.repository.NotificationCategoryRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class NotificationCategoryUtils {
    private final NotificationCategoryRepository notificationCategoryRepository;

    public NotificationCategory getNotificationCategoryPass(String notificationCategoryName) {
        return notificationCategoryRepository.findByCategoryName(notificationCategoryName)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado la categoría de notificación: " + notificationCategoryName));
    }

    public NotificationCategory getNotificationCategoryAttendanceSocket(String notificationCategoryName, String details, String code) {
        String detailsValue = (details == null || details.isBlank()) ? "" : details;
        return notificationCategoryRepository.findByCategoryName(notificationCategoryName)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        code + ",No se ha encontrado la categoría de notificación " + notificationCategoryName
                                + (detailsValue.isEmpty() ? "" : " - " + detailsValue)));
    }
}
