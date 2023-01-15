package com.worksafe.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetDto {

    private String token;
    private String newPassword;
    private String newPasswordConfirm;

}
