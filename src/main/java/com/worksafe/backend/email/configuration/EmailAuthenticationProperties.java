package com.worksafe.backend.email.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.email.authentication")
public class EmailAuthenticationProperties {

    private String username;
    private String password;
    //todo encyrpt and decyript password. Do not put it directly to the yaml file
    //todo or put it to database
}
