package co.com.botech.dto.preoperation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

// NON_NULL: el contrato solo muestra "fugaXNivel" cuando el fuga correspondiente es true.
@Schema(description = "Internal inspection detail")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InternalInspectionDetailDTO {
    @Schema(description = "Oil leak")
    private Boolean fugaAceite;
    @Schema(description = "Oil leak level")
    private String fugaAceiteNivel;
    @Schema(description = "Oil level is correct")
    private Boolean nivelAceiteApropiado;
    @Schema(description = "Brake fluid leak")
    private Boolean fugaLiquidoFreno;
    @Schema(description = "Brake fluid leak level")
    private String fugaLiquidoFrenoNivel;
    @Schema(description = "Brake fluid level is correct")
    private Boolean nivelLiquidoFrenoApropiado;
    @Schema(description = "Coolant level is correct")
    private Boolean nivelAguaRefrigeranteApropiado;
    @Schema(description = "Radiator leak")
    private Boolean fugaRadiador;
    @Schema(description = "Radiator leak level")
    private String fugaRadiadorNivel;
    @Schema(description = "Battery status")
    private Boolean estadoBaterias;
    // El contrato siempre muestra novedades, aunque sea null: se excluye del NON_NULL.
    @JsonInclude(JsonInclude.Include.ALWAYS)
    @Schema(description = "Reported issues")
    private String novedades;
}
