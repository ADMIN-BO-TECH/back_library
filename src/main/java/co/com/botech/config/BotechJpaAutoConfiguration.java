package co.com.botech.config;

import jakarta.persistence.Entity;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ConditionalOnClass({Entity.class, JpaRepository.class})
@EntityScan(basePackages = "co.com.botech.entity")
@EnableJpaRepositories(basePackages = "co.com.botech.repository")
@ComponentScan(basePackages = "co.com.botech.util")
public class BotechJpaAutoConfiguration {
}
