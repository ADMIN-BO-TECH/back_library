package co.com.botech.dto.preoperation;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Kit carretera detail")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KitCarreteraDetailDTO {
    @Schema(description = "Fire extinguisher validity")
    private String extintorVigencia;
    @Schema(description = "Hydraulic jack")
    private String gatoHidraulico;
    @Schema(description = "Cones and flags")
    private String conosBanderolas;
    @Schema(description = "Tool kit")
    private String kitHerramientas;
    @Schema(description = "Flashlight")
    private String linterna;
    @Schema(description = "Lug wrench")
    private String copa;
    @Schema(description = "First aid kit is valid")
    private String botiquinVigente;
}
