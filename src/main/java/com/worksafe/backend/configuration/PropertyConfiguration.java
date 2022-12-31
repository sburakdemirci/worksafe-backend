package com.worksafe.backend.configuration;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.worksafe.backend.dto.configuration.AppSecurityResourcesConfiguration;
import com.worksafe.backend.dto.configuration.AppServerProperties;
import com.worksafe.backend.dto.configuration.EmailAuthenticationProperties;
import com.worksafe.backend.dto.configuration.EmailSenderProperties;
import com.worksafe.backend.dto.configuration.EmailSmtpProperties;
import com.worksafe.backend.dto.configuration.JwtTokenProperties;

@Configuration
public class PropertyConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "app.security.auth.jwt.token")
    public JwtTokenProperties getJwtTokenProperties() {
        return new JwtTokenProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.email.sender")
    public EmailSenderProperties getEmailSenderProperties() {
        return new EmailSenderProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.email.authentication")
    public EmailAuthenticationProperties getEmailAuthenticationProperties() {
        return new EmailAuthenticationProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.email.smtp.config")
    public EmailSmtpProperties getEmailSmtpProperties() {
        return new EmailSmtpProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.security.resources")
    public AppSecurityResourcesConfiguration getAppSecurityResourcesConfiguration() {
        return new AppSecurityResourcesConfiguration();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.server")
    public AppServerProperties getAppServerProperties() {
        return new AppServerProperties();
    }

    @Bean
    public Session session() {
        return Session.getDefaultInstance(getEmailProperties(), getAuthenticator());
    }

    private Authenticator getAuthenticator() {
        EmailAuthenticationProperties emailAuthenticationProperties = getEmailAuthenticationProperties();

        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAuthenticationProperties.getUsername(),
                        emailAuthenticationProperties.getPassword());
            }
        };
    }

    private Properties getEmailProperties() {
        EmailSmtpProperties emailSmtpProperties = getEmailSmtpProperties();
        Properties props = new Properties();
        props.put("mail.smtp.auth", emailSmtpProperties.isAuth());
        props.put("mail.smtp.starttls.enable", emailSmtpProperties.isStartTlsEnable());
        props.put("mail.smtp.ssl.enable", emailSmtpProperties.isSslEnable());
        props.put("mail.smtp.host", emailSmtpProperties.getHost());
        props.put("mail.smtp.port", emailSmtpProperties.getPort());
        return props;
    }


}
