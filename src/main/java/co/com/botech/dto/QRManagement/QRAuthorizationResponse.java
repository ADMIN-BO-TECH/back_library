package co.com.botech.dto.QRManagement;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QRAuthorizationResponse {
    private String message;
    private boolean allowed;
    private String name;
    private String info;
    private String accessType;
}
