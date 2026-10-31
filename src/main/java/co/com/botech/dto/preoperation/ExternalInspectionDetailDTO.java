package co.com.botech.dto.preoperation;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "External inspection detail")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExternalInspectionDetailDTO {
    @Schema(description = "Front lights")
    private String lucesDelanteras;
    @Schema(description = "Front tires")
    private String llantasDelanteras;
    @Schema(description = "Windshield")
    private String panoramicoDelantero;
    @Schema(description = "Front right mirror")
    private String espejoDerechoDelantero;
    @Schema(description = "Front left mirror")
    private String espejoIzquierdoDelantero;
    @Schema(description = "Rearview mirror")
    private String espejoRetrovisor;
    @Schema(description = "Rear window")
    private String panoramicoTrasero;
    @Schema(description = "Rear lights")
    private String lucesTraseras;
    @Schema(description = "Rear tires")
    private String llantasTraseras;
    @Schema(description = "Brakes")
    private String frenos;
    @Schema(description = "Horn")
    private String pitos;
    @Schema(description = "Spare tire")
    private String llantaRepuesto;
    @Schema(description = "Suspension")
    private String suspension;
    @Schema(description = "Warning lights on")
    private String testigosEncendidos;
    @Schema(description = "Reported issues")
    private String novedades;
}
