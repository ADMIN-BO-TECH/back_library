package co.com.botech.dto.preoperation;

import lombok.*;

@Data @Builder(toBuilder = true) @NoArgsConstructor @AllArgsConstructor
public class RoadKitInput {
    private String extintorVigente;
    private String gatoHidraulico;
    private String conosBanderolas;
    private String kitDeHerramienta;
    private String linterna;
    private String copa;
    private String botiquinPrimerosAuxilios;
    private String observaciones;
}
