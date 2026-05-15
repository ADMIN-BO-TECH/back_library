package co.com.botech.repository;

import co.com.botech.entity.NotificationChannel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationChannelRepository extends JpaRepository<NotificationChannel, Long> {
    Optional<NotificationChannel> findByChannelName(String channelName);
}