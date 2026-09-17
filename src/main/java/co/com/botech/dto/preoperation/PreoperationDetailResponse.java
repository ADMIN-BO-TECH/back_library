package co.com.botech.dto.preoperation;

import lombok.*;

import java.time.LocalDate;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PreoperationDetailResponse {
    private Long id;
    private LocalDate date;
    private String hour;
    private String operatorName;
    private String operatorId;
    private String mobileNumber;
    private String plateNumber;
    private Long vehicleId;
    private Integer kilometraje;

    private boolean hasIssues;
    private boolean hasCritical;
    private InternalInspectionDetailDTO internalInspection;
    private ExternalInspectionDetailDTO externalInspection;
    private KitCarreteraDetailDTO kitCarretera;
    private DocumentosDetailDTO documentos;
}
