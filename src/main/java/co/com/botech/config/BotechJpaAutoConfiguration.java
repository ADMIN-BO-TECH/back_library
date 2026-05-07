package co.com.botech.config;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScanPackages;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

@AutoConfiguration
@ConditionalOnClass({Entity.class, JpaRepository.class})
public class BotechJpaAutoConfiguration {

    /**
     * Registra los paquetes de la librería en:
     * - EntityScanPackages → para que Hibernate encuentre las entidades
     * - AutoConfigurationPackages → para que JpaRepositoriesAutoConfiguration encuentre los repos
     *
     * Declarado static para que Spring lo procese antes de instanciar cualquier otro bean.
     */
    @Bean
    public static BeanDefinitionRegistryPostProcessor botechJpaPackagesRegistrar() {
        return new BeanDefinitionRegistryPostProcessor() {
            @Override
            public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
                EntityScanPackages.register(registry, "co.com.botech.entity");
                AutoConfigurationPackages.register(registry, "co.com.botech.repository");
            }

            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
            }
        };
    }
}
