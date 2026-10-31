package co.com.botech.dto.preoperation;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Documentos detail")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class DocumentosDetailDTO {
    @Schema(description = "Operation card")
    private String tarjetaOperacion;
    @Schema(description = "Ownership card")
    private String tarjetaPropiedad;
    @Schema(description = "SOAT insurance")
    private String soat;
    @Schema(description = "Mechanical inspection")
    private String tecnicoMecanica;
    @Schema(description = "RCC and RCE policies")
    private String polizasRccRce;
    @Schema(description = "Driver license")
    private String licenciaConduccion;
    @Schema(description = "Reported issues")
    private String novedades;
}
