package com.worksafe.backend.file.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.file.service")
public class FileServiceProperties {

    private Long signedUrlDurationSeconds;

}
