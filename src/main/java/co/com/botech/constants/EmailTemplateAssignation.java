package co.com.botech.constants;

import co.com.botech.dto.email.EmailVariables;
import co.com.botech.dto.email.templateStructure.SendParentEmailRequest;
import lombok.Getter;

@Getter
public enum EmailTemplateAssignation {

    ADMIN_INFO("admin-information-form-email", null),
    GENERAL_EMAIL("email", null),
    BETA_TESTERS("welcome-beta-testers", null),
    WELCOME_EMPLOYEES("welcome-empleados-email", null),
    WELCOME_PARENTS("welcome-parents-email", SendParentEmailRequest.class);

    private final String templateName;
    private final Class<? extends EmailVariables> variablesClass;

    EmailTemplateAssignation(String templateName,
                             Class<? extends EmailVariables> variablesClass) {
        this.templateName = templateName;
        this.variablesClass = variablesClass;
    }
}