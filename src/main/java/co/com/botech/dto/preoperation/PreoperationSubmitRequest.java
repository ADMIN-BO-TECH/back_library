package co.com.botech.dto.preoperation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetDateTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PreoperationSubmitRequest {

    @NotNull private Long idVehicle;
    private Integer kilometraje;

    @NotNull @Valid private InternalInspectionInput inspeccionInterna;
    @NotNull @Valid private ExternalInspectionInput inspeccionExterna;
    @NotNull @Valid private RoadKitInput kitDeCarretera;
    @NotNull @Valid private DocumentsInput documentos;

    private Boolean aceptoVeracidad;
    private OffsetDateTime timestamp;
    @Valid private GpsLocationInput gpsLocation;
}
