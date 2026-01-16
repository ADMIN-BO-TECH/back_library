package co.com.botech.dto.documentType;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentTypeDTO {
    private Long documentTypeId;
    private String name;
}
