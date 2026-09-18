package co.com.botech.dto.preoperation;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExternalInspectionDetailDTO {
    private String lucesDelanteras;
    private String llantasDelanteras;
    private String panoramicoDelantero;
    private String espejoDerechoDelantero;
    private String espejoIzquierdoDelantero;
    private String espejoRetrovisor;
    private String panoramicoTrasero;
    private String lucesTraseras;
    private String llantasTraseras;
    private String frenos;
    private String pitos;
    private String llantaRepuesto;
    private String suspension;
    private String testigosEncendidos;
    private String novedades;
}
