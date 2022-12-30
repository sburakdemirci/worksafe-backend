package com.worksafe.backend.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.auth.jwt.token")
@Component
public class EmailConfigurationProperties {

    private String secretKey;
    private Long expireLength;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getExpireLength() {
        return expireLength;
    }

    public void setExpireLength(Long expireLength) {
        this.expireLength = expireLength;
    }


}
