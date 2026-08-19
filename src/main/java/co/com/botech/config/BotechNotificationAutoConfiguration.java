package co.com.botech.config;

import co.com.botech.repository.NotificationCategoryRepository;
import co.com.botech.repository.NotificationHistoryRepository;
import co.com.botech.repository.UserDisabledNotificationRepository;
import co.com.botech.repository.UserRepository;
import co.com.botech.util.notification.NotificationDispatcher;
import co.com.botech.util.notification.NotificationService;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;

@AutoConfiguration
@ConditionalOnClass(FirebaseMessaging.class)
@EnableConfigurationProperties(NotificationProperties.class)
public class BotechNotificationAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(FirebaseMessaging.class)
    public NotificationDispatcher notificationDispatcher(FirebaseMessaging firebaseMessaging,
                                                         NotificationProperties props) {
        return new NotificationDispatcher(firebaseMessaging, props);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(name = "notificationExecutor")
    public NotificationService notificationService(NotificationDispatcher dispatcher,
                                                   NotificationProperties props,
                                                   UserRepository userRepository,
                                                   NotificationCategoryRepository categoryRepository,
                                                   NotificationHistoryRepository historyRepository,
                                                   UserDisabledNotificationRepository disabledRepository,
                                                   @Qualifier("notificationExecutor") Executor executor) {
        return new NotificationService(
                dispatcher,
                props,
                userRepository,
                categoryRepository,
                historyRepository,
                disabledRepository,
                executor
        );
    }
}
