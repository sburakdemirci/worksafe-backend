package com.worksafe.backend.email.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.email.smtp.config")
public class EmailSmtpProperties {

    private String host;
    private int port;
    private boolean auth;
    private boolean startTlsEnable;
    private boolean sslEnable;

}
