package co.com.botech.dto.QRManagement;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QRAuthorizationRequest {
    private String document;
    private Long rfidRegisterId;
}
