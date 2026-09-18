package co.com.botech.dto.preoperation;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class InternalInspectionCatalog {
    private List<String> leakStatus;
    private List<String> fluidLevel;
    private List<InternalCatalogFieldDTO> fields;
}
