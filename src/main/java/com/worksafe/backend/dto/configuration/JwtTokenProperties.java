package com.worksafe.backend.dto.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenProperties {

    private String secretKey;
    private Long expireLength;

}
