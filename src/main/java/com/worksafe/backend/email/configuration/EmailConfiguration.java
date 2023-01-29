package com.worksafe.backend.email.configuration;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class EmailConfiguration {

    private final EmailAuthenticationProperties emailAuthenticationProperties;
    private final EmailSmtpProperties emailSmtpProperties;


    @Bean
    public Session session() {
        return Session.getDefaultInstance(getEmailProperties(), getAuthenticator());
    }

    private Authenticator getAuthenticator() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAuthenticationProperties.getUsername(),
                        emailAuthenticationProperties.getPassword());
            }
        };
    }

    private Properties getEmailProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", emailSmtpProperties.isAuth());
        props.put("mail.smtp.starttls.enable", emailSmtpProperties.isStartTlsEnable());
        props.put("mail.smtp.ssl.enable", emailSmtpProperties.isSslEnable());
        props.put("mail.smtp.host", emailSmtpProperties.getHost());
        props.put("mail.smtp.port", emailSmtpProperties.getPort());
        return props;
    }


}
