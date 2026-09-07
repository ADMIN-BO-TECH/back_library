package co.com.botech.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Verifica la conectividad real con la base de datos MySQL obteniendo una conexión del pool
 * y ejecutando {@code connection.isValid()} con un timeout corto.
 *
 * <p><b>UP</b>: conexión obtenida y validada en menos de {@value #TIMEOUT_SECONDS} segundos.<br>
 * <b>DOWN</b>: no se pudo obtener la conexión, la validación devolvió {@code false}, o se agotó
 * el timeout — el contenedor no está listo para servir tráfico.
 *
 * <p>Diseñado para el grupo {@code readiness}. Configura el grupo en el {@code application.yml}
 * de cada microservicio:<pre>
 *   management.endpoint.health.group.readiness.include: readiness-state,database,firebase
 * </pre>
 * Para deshabilitar este indicador puntualmente:
 * {@code management.health.database.enabled=false}
 */
public class DatabaseHealthIndicator extends AbstractHealthIndicator {

    static final int TIMEOUT_SECONDS = 2;

    private final DataSource dataSource;

    public DatabaseHealthIndicator(DataSource dataSource) {
        super("Database health check failed");
        this.dataSource = dataSource;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            boolean valid = connection.isValid(TIMEOUT_SECONDS);
            if (valid) {
                builder.up()
                        .withDetail("validationQuery", "connection.isValid(" + TIMEOUT_SECONDS + "s)");
            } else {
                builder.down()
                        .withDetail("reason", "connection.isValid returned false within " + TIMEOUT_SECONDS + "s");
            }
        }
    }
}
