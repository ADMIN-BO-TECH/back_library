package co.com.botech.dto.email.templateStructure;

import co.com.botech.dto.email.EmailVariables;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendProductKeyEmailRequest implements EmailVariables {
    private String familyCode;
    private String productKey;
    private String schoolName;
    private String parentName;
    private String expiresAt;
}
