package co.com.botech.dto.preoperation;

import lombok.*;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ExternalInspectionInput {
    private String lucesDelanteras;
    private String llantasDelanteras;
    private String panoramicoDelantero;
    private String espejoDelanteroDerecho;
    private String espejoDelanteroIzquierdo;
    private String espejoRetrovisor;
    private String panoramicoTrasero;
    private String lucesTraseras;
    private String llantasTraseras;
    private String frenos;
    private String pitos;
    private String llantaDeRepuesto;
    private String suspension;
    private String testigosEncendidos;
    private String observaciones;
}
