package co.com.botech.dto.preoperation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InternalCatalogFieldDTO {
    private String inputField;
    private String label;
    private Boolean hasLevel;
    private String type;
}
