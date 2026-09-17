package co.com.botech.dto.preoperation;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class KitCatalog {
    private List<String> status;
    private List<CatalogFieldDTO> fields;
}
