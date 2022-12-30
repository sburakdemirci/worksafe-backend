package com.worksafe.backend.dto.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailSenderProperties {
    private String email;
    private String alias;
}
