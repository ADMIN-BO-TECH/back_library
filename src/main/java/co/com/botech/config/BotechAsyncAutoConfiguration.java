package co.com.botech.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@AutoConfiguration
@EnableAsync
public class BotechAsyncAutoConfiguration {

    @Bean(name = "routeSyncExecutor")
    @ConditionalOnMissingBean(name = "routeSyncExecutor")
    public Executor routeSyncExecutor() {
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setCorePoolSize(2);
        exec.setMaxPoolSize(4);
        exec.setQueueCapacity(200);
        exec.setThreadNamePrefix("route-sync-");
        exec.initialize();
        return exec;
    }

    @Bean(name = "notificationExecutor")
    @ConditionalOnMissingBean(name = "notificationExecutor")
    public Executor notificationExecutor() {
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setCorePoolSize(2);
        exec.setMaxPoolSize(6);
        exec.setQueueCapacity(500);
        exec.setThreadNamePrefix("notif-send-");
        exec.initialize();
        return exec;
    }
}
