package co.com.botech.dto.preoperation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

// NON_NULL: el contrato solo muestra "fugaXNivel" cuando el fuga correspondiente es true.
@Data @Builder @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InternalInspectionDetailDTO {
    private Boolean fugaAceite;
    private String fugaAceiteNivel;
    private Boolean nivelAceiteApropiado;
    private Boolean fugaLiquidoFreno;
    private String fugaLiquidoFrenoNivel;
    private Boolean nivelLiquidoFrenoApropiado;
    private Boolean nivelAguaRefrigeranteApropiado;
    private Boolean fugaRadiador;
    private String fugaRadiadorNivel;
    private Boolean estadoBaterias;
    private String novedades;
}
