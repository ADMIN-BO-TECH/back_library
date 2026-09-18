package co.com.botech.dto.preoperation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PreoperationCatalogResponse {
    private ExternalInspectionCatalog externalInspection;
    private InternalInspectionCatalog internalInspection;
    private KitCatalog kit;
    private DocumentsCatalog documents;
}
