package co.com.botech.dto.stop.n8n;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Stop basic info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StopBasicInfoResponse {
    @Schema(description = "Name")
    private String name;
}
