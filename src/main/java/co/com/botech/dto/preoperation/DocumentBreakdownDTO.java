package co.com.botech.dto.preoperation;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class DocumentBreakdownDTO {
    private String document;
    private String documentLabel;
    private long vigente;
    private long noVigente;
    private long total;
    private double compliantRate;
    private double nonCompliantRate;
}
