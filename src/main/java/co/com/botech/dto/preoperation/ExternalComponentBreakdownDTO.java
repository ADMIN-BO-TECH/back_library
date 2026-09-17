package co.com.botech.dto.preoperation;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExternalComponentBreakdownDTO {
    private String component;
    private String componentLabel;
    private long total;
    private long bueno;
    private long malo;
    private long danado;
    private double buenoRate;
    private double attentionRate;
    private double criticalRate;
    private double problemRate;
}
