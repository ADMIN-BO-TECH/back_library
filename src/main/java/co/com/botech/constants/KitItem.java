package co.com.botech.constants;

import lombok.Getter;

@Getter
public enum KitItem {
    EXTINTOR_VIGENCIA("extintorVigente", "extintor_vigencia", "Extintor y vigencia"),
    GATO_HIDRAULICO("gatoHidraulico", "gato_hidraulico", "Gato hidráulico"),
    CONOS_BANDEROLAS("conosBanderolas", "conos_banderolas", "Conos / banderolas"),
    KIT_HERRAMIENTAS("kitDeHerramienta", "kit_herramientas", "Kit de herramientas"),
    LINTERNA("linterna", "linterna", "Linterna"),
    COPA("copa", "copa", "Copa"),
    BOTIQUIN_VIGENTE("botiquinPrimerosAuxilios", "botiquin_vigente", "Botiquín de primeros auxilios");

    private final String inputField;
    private final String key;
    private final String label;

    KitItem(String inputField, String key, String label) {
        this.inputField = inputField;
        this.key = key;
        this.label = label;
    }

    public static KitItem fromKey(String key) {
        for (KitItem i : values()) {
            if (i.key.equals(key)) return i;
        }
        throw new IllegalArgumentException("Item de kit no reconocido: " + key);
    }
}
