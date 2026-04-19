package co.com.botech.dto.email.templateStructure;

import co.com.botech.dto.email.EmailVariables;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendBetaTestersEmailRequest implements EmailVariables {
    private String familyCode;
    private String siteUrl;
    private String testFlightUrl;
    private String androidBetaUrl;
    private String createAccountUrl;
    private String supportEmail;
    private String helpUrl;
    private String unsubscribeUrl;
    private String companyAddress;
    private String countryCity;
}

