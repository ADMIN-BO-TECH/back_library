package co.com.botech.dto.preoperation;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Pre-operational check period")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PreopPeriodDTO {
    @Schema(description = "Period start")
    private String from;
    @Schema(description = "Period end")
    private String to;
}
