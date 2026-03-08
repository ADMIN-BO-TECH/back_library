package co.com.botech.constants;

public enum AttendanceTypeConstants {
    SUBIDA_BUS("Subida Bus"),
    BAJADA_BUS("Bajada Bus"),
    ENTRADA_PORTERIA("Entrada Portería"),
    SALIDA_PORTERIA("Salida Portería");
    private final String descripcion;

    AttendanceTypeConstants(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
