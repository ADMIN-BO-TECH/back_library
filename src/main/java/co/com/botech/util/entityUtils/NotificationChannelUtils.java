package co.com.botech.util.entityUtils;

import co.com.botech.constants.CustomExceptionCodeConstants;
import co.com.botech.entity.NotificationChannel;
import co.com.botech.repository.NotificationChannelRepository;
import co.com.botech.util.generalUtils.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class NotificationChannelUtils {
    private final NotificationChannelRepository notificationChannelRepository;

    public NotificationChannel getNotificationChannelOrThrow(String channelName) {
        return notificationChannelRepository.findByChannelName(channelName)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        "No se ha encontrado el canal de notificación: " + channelName));
    }

    public NotificationChannel getNotificationChannelOrThrow(String channelName, String details, String code) {
        String detailsValue = (details == null || details.isBlank()) ? "" : details;
        return notificationChannelRepository.findByChannelName(channelName)
                .orElseThrow(() -> new CustomException(CustomExceptionCodeConstants.ENTITY_NOT_FOUND,
                        code + ",No se ha encontrado el canal de notificación " + channelName
                                + (detailsValue.isEmpty() ? "" : " - " + detailsValue)));
    }
}