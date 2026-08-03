package co.com.botech.constants;

import co.com.botech.dto.email.EmailVariables;
import co.com.botech.dto.email.templateStructure.SendParentEmailRequest;
import co.com.botech.dto.email.templateStructure.SendProductKeyEmailRequest;
import lombok.Getter;

@Getter
public enum EmailTemplateAssignation {

    ADMIN_INFO("admin-information-form-email.html", null),
    GENERAL_EMAIL("email", null),
    BETA_TESTERS("welcome-beta-testers.html", null),
    WELCOME_EMPLOYEES("welcome-empleados-email.html", null),
    WELCOME_PARENTS("welcome-parents-email.html", SendParentEmailRequest.class),
    PRODUCT_KEY_DELIVERY("product-key-delivery.html", SendProductKeyEmailRequest.class);

    private final String templateName;
    private final Class<? extends EmailVariables> variablesClass;

    EmailTemplateAssignation(String templateName,
                             Class<? extends EmailVariables> variablesClass) {
        this.templateName = templateName;
        this.variablesClass = variablesClass;
    }
}
