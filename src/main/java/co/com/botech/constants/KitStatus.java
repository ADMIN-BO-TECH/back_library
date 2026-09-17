package co.com.botech.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum KitStatus {
    SI("si"), NO("no"), NOVEDAD("novedad");

    private final String wireValue;
    KitStatus(String wireValue) { this.wireValue = wireValue; }

    @JsonCreator
    public static KitStatus fromInput(String raw) {
        if (raw == null) throw new IllegalArgumentException("Valor de kit requerido");
        return switch (raw.trim().toUpperCase()) {
            case "SI" -> SI;
            case "NO" -> NO;
            case "NOVEDAD" -> NOVEDAD;
            default -> throw new IllegalArgumentException("Valor de kit no reconocido: " + raw);
        };
    }
}
