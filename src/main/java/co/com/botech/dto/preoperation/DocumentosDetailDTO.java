package co.com.botech.dto.preoperation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentosDetailDTO {
    private String tarjetaOperacion;
    private String tarjetaPropiedad;
    private String soat;
    private String tecnicoMecanica;
    private String polizasRccRce;
    private String licenciaConduccion;
    private String novedades;
}
