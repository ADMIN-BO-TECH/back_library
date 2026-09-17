package co.com.botech.dto.preoperation;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class DocumentsInput {
    private String tarjetaDeOperacion;
    private String tarjetaDePropiedad;
    private String soat;
    private String tecnomecanica;
    private String polizasRccRce;
    private String licenciaDeConduccion;
    private String observaciones;
}
