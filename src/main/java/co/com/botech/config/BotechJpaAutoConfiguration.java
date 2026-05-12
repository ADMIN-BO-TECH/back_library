package co.com.botech.config;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;

@AutoConfiguration
@ConditionalOnClass({Entity.class, JpaRepository.class})
@EntityScan(basePackages = "co.com.botech.entity")
@ComponentScan(basePackages = "co.com.botech.util")
public class BotechJpaAutoConfiguration {

    @Bean
    public static BeanDefinitionRegistryPostProcessor botechJpaPackagesRegistrar() {
        return new BeanDefinitionRegistryPostProcessor() {
            @Override
            public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
                AutoConfigurationPackages.register(registry, "co.com.botech.repository");
            }

            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
            }
        };
    }
}
