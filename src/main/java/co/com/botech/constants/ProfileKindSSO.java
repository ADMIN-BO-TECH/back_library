package co.com.botech.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProfileKindSSO {
    ADMIN_PASS("ADMIN-PASS"),
    GUARDA_PASS("GUARDA-PASS"),
    ADMIN_SEGUIMIENTO("ADMIN-SEGUIMIENTO"),
    COORD_COLEGIO_SEGUIMIENTO("COORD-COLEGIO-SEGUIMIENTO"),
    COORD_MANTENIMIENTO_SEGUIMIENTO("COORD-MANTENIMIENTO-SEGUIMIENTO"),
    JEFE_TALLER_SEGUIMIENTO("JEFE-TALLER-SEGUIMIENTO"),
    PADRE_TRACKING("PADRE-TRACKING"),
    EMPLEADO_TRACKING("EMPLEADO-TRACKING"),
    ESTUDIANTE_TRACKING("ESTUDIANTE-TRACKING"),
    LINKS("LINKS"),
    AUXILIAR_CARDINAL("AUXILIAR-CARDINAL"),
    OPERARIO_CARDINAL("OPERARIO-CARDINAL"),
    RRHH_PASS("RRHH-PASS"),
    PADRE_N8N("PADRE-N8N"),
    ADMIN_BOSS("ADMIN-BOSS"),
    ASISESCUELA_PASS("ASISESCUELA-PASS"),

    /**
     * Actor interno, NO es un perfil de sesión: nadie inicia sesión como SISTEMA.
     * Se usa solo en el historial de mantenimiento cuando una acción la dispara
     * el propio sistema (por ejemplo, el cierre automático de la OM desde la
     * orden de servicio en Fase 2).
     */
    SISTEMA("SISTEMA");

    private final String slug;
}