package co.com.botech.dto.preoperation;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class InternalInspectionInput {
    private String fugaDeAceite;
    private String nivelDeAceite;
    private String fugaLiquidoFrenos;
    private String nivelLiquidoFrenos;
    private String fugasDeRadiador;
    private String nivelAguaRefrigerante;
    private String estadoDeBaterias;
    private String observaciones;
}
