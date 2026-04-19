package co.com.botech.dto.email.templateStructure;

import co.com.botech.dto.email.EmailVariables;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendEmployeeEmailRequest implements EmailVariables {
    private String familyCode;
    private String schoolName;
    private String siteUrl;
    private String countryCity;
}
