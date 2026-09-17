package co.com.botech.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum DocumentValidity {
    VIGENTE("vigente"), NO_VIGENTE("no_vigente");

    private final String wireValue;
    DocumentValidity(String wireValue) { this.wireValue = wireValue; }

    @JsonCreator
    public static DocumentValidity fromInput(String raw) {
        if (raw == null) throw new IllegalArgumentException("Valor de documento requerido");
        String normalized = raw.trim().toUpperCase().replace(" ", "_");
        return switch (normalized) {
            case "VIGENTE" -> VIGENTE;
            case "NO_VIGENTE" -> NO_VIGENTE;
            default -> throw new IllegalArgumentException("Valor de documento no reconocido: " + raw);
        };
    }
}
