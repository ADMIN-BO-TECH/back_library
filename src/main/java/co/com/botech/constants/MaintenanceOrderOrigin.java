package co.com.botech.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Quién originó la OM. Se deriva del rol del creador, no del body. */
@Getter
@RequiredArgsConstructor
public enum MaintenanceOrderOrigin {
    COLEGIO("Colegio"),
    MANTENIMIENTO("Mantenimiento");

    private final String label;
}
