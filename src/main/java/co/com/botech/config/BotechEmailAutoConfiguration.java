package co.com.botech.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;

import java.util.Properties;

@AutoConfiguration
@ConditionalOnClass({JavaMailSender.class, TemplateEngine.class})
@ConditionalOnProperty(prefix = "app.email", name = "username")
@EnableConfigurationProperties(EmailProperties.class)
@ComponentScan(basePackages = "co.com.botech.util.email")
public class BotechEmailAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public JavaMailSender javaMailSender(EmailProperties props) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(props.getHost());
        sender.setPort(props.getPort());
        sender.setUsername(props.getUsername());
        sender.setPassword(props.getPassword());

        Properties mailProps = sender.getJavaMailProperties();
        mailProps.put("mail.smtp.auth", String.valueOf(props.isAuth()));
        mailProps.put("mail.smtp.starttls.enable", String.valueOf(props.isStarttls()));
        mailProps.put("mail.smtp.ssl.trust", props.getTrust());
        mailProps.put("mail.transport.protocol", "smtp");
        return sender;
    }
}