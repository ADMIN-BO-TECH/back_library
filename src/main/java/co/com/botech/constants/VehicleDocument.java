package co.com.botech.constants;

import lombok.Getter;

@Getter
public enum VehicleDocument {
    TARJETA_OPERACION("tarjetaDeOperacion", "tarjeta_operacion", "Tarjeta de operación"),
    TARJETA_PROPIEDAD("tarjetaDePropiedad", "tarjeta_propiedad", "Tarjeta de propiedad"),
    SOAT("soat", "soat", "SOAT"),
    TECNICO_MECANICA("tecnomecanica", "tecnico_mecanica", "Técnico mecánica"),
    POLIZAS_RCC_RCE("polizasRccRce", "polizas_rcc_rce", "Pólizas RCC/RCE"),
    LICENCIA_CONDUCCION("licenciaDeConduccion", "licencia_conduccion", "Licencia de conducción");

    private final String inputField;
    private final String key;
    private final String label;

    VehicleDocument(String inputField, String key, String label) {
        this.inputField = inputField;
        this.key = key;
        this.label = label;
    }

    public static VehicleDocument fromKey(String key) {
        for (VehicleDocument d : values()) {
            if (d.key.equals(key)) return d;
        }
        throw new IllegalArgumentException("Documento no reconocido: " + key);
    }
}
