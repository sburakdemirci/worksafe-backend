package com.worksafe.backend.dto.configuration;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppSecurityResourcesConfiguration {

    private List<String> unauthorizedPatterns;
}
