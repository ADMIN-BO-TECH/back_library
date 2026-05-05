package co.com.botech.dto.QRManagement;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QRVehicleRequest {
    private String document;
    private Long idVehicle;
}
