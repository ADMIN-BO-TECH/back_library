package co.com.botech.util.email;

import co.com.botech.config.EmailProperties;
import co.com.botech.constants.EmailTemplateAssignation;
import co.com.botech.dto.email.EmailVariables;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class EmailDispatcher {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final EmailProperties props;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendHtml(String to, String subject, String html) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(resolveFrom());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);
            mailSender.send(message);
        } catch (Exception e) {
            throw new EmailSendException("Failed sending HTML email to " + to, e);
        }
    }

    public void sendWithTemplate(String to, String subject, String templateName, Map<String, Object> variables) {
        Context ctx = new Context();
        if (variables != null) variables.forEach(ctx::setVariable);
        String html = templateEngine.process(templateName, ctx);
        sendHtml(to, subject, html);
    }

    public <T extends EmailVariables> void sendWithTemplate(
            String to,
            String subject,
            EmailTemplateAssignation template,
            T variables
    ) {
        Map<String, Object> vars = variables == null
                ? Map.of()
                : objectMapper.convertValue(variables, new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {});
        sendWithTemplate(to, subject, template.getTemplateName().replace(".html", ""), vars);
    }

    private String resolveFrom() {
        return props.getFrom() != null && !props.getFrom().isBlank()
                ? props.getFrom()
                : props.getUsername();
    }
}
