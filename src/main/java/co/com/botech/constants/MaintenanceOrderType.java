package co.com.botech.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Tipo de la orden de mantenimiento (OM). */
@Getter
@RequiredArgsConstructor
public enum MaintenanceOrderType {
    LATONERIA_PINTURA("Latonería y pintura"),
    TALLER("Taller");

    private final String label;
}
