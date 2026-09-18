package co.com.botech.dto.preoperation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CatalogFieldDTO {
    private String inputField;
    private String label;
}
