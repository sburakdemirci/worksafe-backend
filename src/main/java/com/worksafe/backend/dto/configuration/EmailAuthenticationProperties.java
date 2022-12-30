package com.worksafe.backend.dto.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailAuthenticationProperties {

    private String username;
    private String password;
    //todo encyrpt and decyript password. Do not put it directly to the yaml file
}
