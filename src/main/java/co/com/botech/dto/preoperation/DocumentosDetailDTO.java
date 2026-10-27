package co.com.botech.dto.preoperation;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class DocumentosDetailDTO {
    private String tarjetaOperacion;
    private String tarjetaPropiedad;
    private String soat;
    private String tecnicoMecanica;
    private String polizasRccRce;
    private String licenciaConduccion;
    private String novedades;
}
