package co.com.botech.dto.preoperation;

import lombok.*;

import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class DocumentsResponse {
    private List<DocumentBreakdownDTO> documents;
    private long noveltyCount;
}
