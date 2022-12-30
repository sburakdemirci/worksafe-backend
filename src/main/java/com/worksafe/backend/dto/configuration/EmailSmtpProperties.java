package com.worksafe.backend.dto.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailSmtpProperties {

    private String host;
    private int port;
    private boolean auth;
    private boolean startTlsEnable;
    private boolean sslEnable;

}
