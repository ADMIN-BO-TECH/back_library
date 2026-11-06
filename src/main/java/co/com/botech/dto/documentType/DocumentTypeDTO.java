package co.com.botech.dto.documentType;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Document type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentTypeDTO {
    @Schema(description = "Document type ID")
    private Long documentTypeId;
    @Schema(description = "Code")
    private String code;
    @Schema(description = "Name")
    private String name;
}
