package com.worksafe.backend.email.helper;

import org.springframework.stereotype.Component;

import com.worksafe.backend.core.security.configuration.AppServerProperties;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UrlConstructHelper {

    private final AppServerProperties appServerProperties;


    public String constructVerificationUrl(String token) {
        return appServerProperties.getUrl() + "/authentication/verify-user?token=" + token;
    }

    public String constructPasswordResetUrl(String token) {
        return appServerProperties.getUrl() + "/authentication/reset-password?token=" + token;
    }

}
