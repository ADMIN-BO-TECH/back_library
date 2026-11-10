package co.com.botech.constants;

import lombok.Getter;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * Estados de la OM y su matriz de transiciones (única fuente de verdad).
 * DEVUELTA = la solicitud vuelve al coordinador de colegio para corregirla.
 * Quién puede ejecutar cada transición lo decide el servicio, no este enum.
 */
@Getter
public enum MaintenanceOrderStatus {
    SOLICITADA("Solicitada"),
    APROBADA("Aprobada"),
    POSPUESTA("Pospuesta"),
    RECHAZADA("Rechazada"),
    EN_TALLER("En taller"),
    DEVUELTA("Devuelta"),
    CERRADA("Cerrada");

    /** Estados en los que la OM sigue abierta (base de la regla de duplicados al crear). */
    public static final Set<MaintenanceOrderStatus> OPEN_STATUSES = Collections.unmodifiableSet(
            EnumSet.of(SOLICITADA, APROBADA, POSPUESTA, DEVUELTA, EN_TALLER));

    private final String label;

    MaintenanceOrderStatus(String label) {
        this.label = label;
    }

    public Set<MaintenanceOrderStatus> allowedNext() {
        return switch (this) {
            case SOLICITADA -> EnumSet.of(APROBADA, POSPUESTA, DEVUELTA, RECHAZADA);
            case DEVUELTA -> EnumSet.of(SOLICITADA, RECHAZADA);
            case POSPUESTA -> EnumSet.of(APROBADA, RECHAZADA);
            case APROBADA -> EnumSet.of(EN_TALLER, CERRADA);
            case EN_TALLER -> EnumSet.of(CERRADA);
            case RECHAZADA, CERRADA -> EnumSet.noneOf(MaintenanceOrderStatus.class);
        };
    }

    public boolean canTransitionTo(MaintenanceOrderStatus next) {
        return next != null && allowedNext().contains(next);
    }

    public boolean isTerminal() {
        return allowedNext().isEmpty();
    }

    /** Pasar A este estado exige un motivo. */
    public boolean requiresReason() {
        return this == POSPUESTA || this == DEVUELTA || this == RECHAZADA;
    }
}
