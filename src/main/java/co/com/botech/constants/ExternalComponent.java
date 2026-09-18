package co.com.botech.constants;

import lombok.Getter;

/** key/label + el nombre del campo tal como llega en el payload de submit. */
@Getter
public enum ExternalComponent {
    LUCES_DELANTERAS("lucesDelanteras", "luces_delanteras", "Luces delanteras"),
    LLANTAS_DELANTERAS("llantasDelanteras", "llantas_delanteras", "Llantas delanteras"),
    PANORAMICO_DELANTERO("panoramicoDelantero", "panoramico_delantero", "Panorámico delantero"),
    ESPEJO_DERECHO_DELANTERO("espejoDelanteroDerecho", "espejo_derecho_delantero", "Espejo derecho delantero"),
    ESPEJO_IZQUIERDO_DELANTERO("espejoDelanteroIzquierdo", "espejo_izquierdo_delantero", "Espejo izquierdo delantero"),
    ESPEJO_RETROVISOR("espejoRetrovisor", "espejo_retrovisor", "Espejo retrovisor"),
    PANORAMICO_TRASERO("panoramicoTrasero", "panoramico_trasero", "Panorámico trasero"),
    LUCES_TRASERAS("lucesTraseras", "luces_traseras", "Luces traseras"),
    LLANTAS_TRASERAS("llantasTraseras", "llantas_traseras", "Llantas traseras"),
    FRENOS("frenos", "frenos", "Frenos"),
    PITOS("pitos", "pitos", "Pitos"),
    LLANTA_REPUESTO("llantaDeRepuesto", "llanta_repuesto", "Llanta de repuesto"),
    SUSPENSION("suspension", "suspension", "Suspensión"),
    TESTIGOS_ENCENDIDOS("testigosEncendidos", "testigos_encendidos", "Testigos encendidos");

    private final String inputField;
    private final String key;
    private final String label;

    ExternalComponent(String inputField, String key, String label) {
        this.inputField = inputField;
        this.key = key;
        this.label = label;
    }

    public static ExternalComponent fromKey(String key) {
        for (ExternalComponent c : values()) {
            if (c.key.equals(key)) return c;
        }
        throw new IllegalArgumentException("Componente externo no reconocido: " + key);
    }
}
