package co.com.botech.dto.preoperation;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class InternalFieldBreakdownDTO {
    private String field;
    private String fieldLabel;
    private long problematic;
    private long ok;
    private long total;
    private double problemRate;
    private long levelNormal;
    private long levelBajo;
}
