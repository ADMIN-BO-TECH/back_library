package co.com.botech.constants;

import lombok.Getter;

@Getter
public enum InternalField {
    FUGA_ACEITE("fuga_aceite", "Fuga de aceite"),
    NIVEL_ACEITE_APROPIADO("nivel_aceite_apropiado", "Nivel de aceite apropiado"),
    FUGA_LIQUIDO_FRENO("fuga_liquido_freno", "Fuga de líquido de frenos"),
    NIVEL_LIQUIDO_FRENO_APROPIADO("nivel_liquido_freno_apropiado", "Nivel de líquido de frenos apropiado"),
    FUGA_RADIADOR("fuga_radiador", "Fuga de radiador"),
    NIVEL_AGUA_REFRIGERANTE_APROPIADO("nivel_agua_refrigerante_apropiado", "Nivel de agua refrigerante apropiado"),
    ESTADO_BATERIAS("estado_baterias", "Estado de baterías");

    private final String key;
    private final String label;

    InternalField(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public static InternalField fromKey(String key) {
        for (InternalField f : values()) {
            if (f.key.equals(key)) return f;
        }
        throw new IllegalArgumentException("Campo de inspeccion interna no reconocido: " + key);
    }
}
