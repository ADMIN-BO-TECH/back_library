package co.com.botech.dto.preoperation;

import lombok.*;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Documents")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class DocumentsResponse {
    @Schema(description = "Documents")
    private List<DocumentBreakdownDTO> documents;
    @Schema(description = "Number of reported issues")
    private long noveltyCount;
}
