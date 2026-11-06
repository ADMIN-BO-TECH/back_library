package co.com.botech.dto.preoperation;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Internal inspection")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class InternalInspectionResponse {
    @Schema(description = "Fields")
    private List<InternalFieldBreakdownDTO> fields;
    @Schema(description = "Number of reported issues")
    private long noveltyCount;
}
