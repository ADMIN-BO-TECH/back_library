package co.com.botech.dto.preoperation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PreoperationSubmitRequest {

    @NotNull private Long driverId;
    @NotNull private Long idVehicle;
    @NotNull private LocalDate fecha;
    @NotNull private LocalTime hora;
    @NotBlank private String operador;
    @NotBlank private String cedula;
    @NotBlank private String colegio;
    private String movil;
    @NotBlank private String placa;
    private Integer kilometraje;

    @NotNull @Valid private InternalInspectionInput inspeccionInterna;
    @NotNull @Valid private ExternalInspectionInput inspeccionExterna;
    @NotNull @Valid private RoadKitInput kitDeCarretera;
    @NotNull @Valid private DocumentsInput documentos;

    private Boolean aceptoVeracidad;
    private OffsetDateTime timestamp;
    @Valid private GpsLocationInput gpsLocation;
}
