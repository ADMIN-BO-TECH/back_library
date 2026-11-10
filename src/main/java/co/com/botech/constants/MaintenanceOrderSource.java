package co.com.botech.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** De dónde salió el daño reportado. */
@Getter
@RequiredArgsConstructor
public enum MaintenanceOrderSource {
    PREOPERACION("Preoperación"),
    REPORTE_EMPLEADO("Reporte de empleado"),
    REPORTE_COLEGIO("Reporte de colegio"),
    PATIO_TALLER("Patio / taller");

    private final String label;
}
