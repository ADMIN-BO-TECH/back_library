package co.com.botech.config;

import jakarta.persistence.Entity;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;

@AutoConfiguration
@ConditionalOnClass({Entity.class, JpaRepository.class})
@ComponentScan(basePackages = {"co.com.botech.util", "co.com.botech.security"})
public class BotechJpaAutoConfiguration {
}
