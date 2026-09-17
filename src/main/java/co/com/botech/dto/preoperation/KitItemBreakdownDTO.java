package co.com.botech.dto.preoperation;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KitItemBreakdownDTO {
    private String item;
    private String itemLabel;
    private long si;
    private long no;
    private long novedad;
    private long total;
    private double compliantRate;
    private double noRate;
    private double novedadRate;
}
