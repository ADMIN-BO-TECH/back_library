package co.com.botech.config;

import co.com.botech.health.DatabaseHealthIndicator;
import co.com.botech.health.FirebaseHealthIndicator;
import com.google.firebase.FirebaseApp;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * Auto-configuración de indicadores de salud provistos por {@code botech-back-library}.
 *
 * <p>Solo se activa cuando Spring Boot Actuator ({@link HealthIndicator}) está presente en el
 * classpath. Cada indicador es condicional a su dependencia y puede deshabilitarse individualmente
 * en el {@code application.yml} del microservicio sin tocar código:
 * <ul>
 *   <li>{@code management.health.database.enabled=false} — deshabilita {@link DatabaseHealthIndicator}</li>
 *   <li>{@code management.health.firebase.enabled=false} — deshabilita {@link FirebaseHealthIndicator}</li>
 * </ul>
 */
@AutoConfiguration(after = BotechFirebaseAutoConfiguration.class)
@ConditionalOnClass(HealthIndicator.class)
public class BackLibraryHealthAutoConfiguration {

    /**
     * Indicador de conectividad MySQL. Registrado bajo el nombre {@code database} en
     * {@code /actuator/health}. Solo se crea si hay un {@link DataSource} disponible.
     */
    @Bean
    @ConditionalOnBean(DataSource.class)
    @ConditionalOnEnabledHealthIndicator("database")
    public DatabaseHealthIndicator databaseHealthIndicator(DataSource dataSource) {
        return new DatabaseHealthIndicator(dataSource);
    }

    /**
     * Indicador de inicialización de Firebase. Registrado bajo el nombre {@code firebase} en
     * {@code /actuator/health}. Solo se crea si hay un {@link FirebaseApp} disponible.
     */
    @Bean
    @ConditionalOnBean(FirebaseApp.class)
    @ConditionalOnEnabledHealthIndicator("firebase")
    public FirebaseHealthIndicator firebaseHealthIndicator(FirebaseApp firebaseApp) {
        return new FirebaseHealthIndicator(firebaseApp);
    }
}
