package co.com.botech.health;

import com.google.firebase.FirebaseApp;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * Verifica que {@link FirebaseApp} esté correctamente inicializado sin realizar llamadas de red,
 * lo que mantiene el probe barato y predecible en tiempo.
 *
 * <p><b>UP</b>: la instancia de {@code FirebaseApp} existe y está activa; Firestore, Cloud
 * Storage y Cloud Messaging pueden ser invocados.<br>
 * <b>DOWN</b>: la app no se inicializó o fue destruida, lo que impediría cualquier operación
 * sobre servicios de Firebase.
 *
 * <p>Diseñado para el grupo {@code readiness}. Configura el grupo en el {@code application.yml}
 * de cada microservicio:<pre>
 *   management.endpoint.health.group.readiness.include: readiness-state,database,firebase
 * </pre>
 * Para deshabilitar este indicador puntualmente:
 * {@code management.health.firebase.enabled=false}
 */
public class FirebaseHealthIndicator extends AbstractHealthIndicator {

    private final FirebaseApp firebaseApp;

    public FirebaseHealthIndicator(FirebaseApp firebaseApp) {
        super("Firebase health check failed");
        this.firebaseApp = firebaseApp;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        String appName = firebaseApp.getName();
        builder.up().withDetail("app", appName);
    }
}
