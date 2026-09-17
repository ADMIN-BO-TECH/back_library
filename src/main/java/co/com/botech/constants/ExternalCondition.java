package co.com.botech.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum ExternalCondition {
    BUENO("bueno"),
    MALO("malo"),
    DANADO("danado");

    private final String wireValue;

    ExternalCondition(String wireValue) { this.wireValue = wireValue; }

    @JsonCreator
    public static ExternalCondition fromInput(String raw) {
        if (raw == null) throw new IllegalArgumentException("Valor de inspeccion externa requerido");
        String normalized = raw.trim().toUpperCase().replace("Ñ", "N");
        return switch (normalized) {
            case "BUENO" -> BUENO;
            case "MALO" -> MALO;
            case "DANADO" -> DANADO;
            default -> throw new IllegalArgumentException("Valor de inspeccion externa no reconocido: " + raw);
        };
    }
}
