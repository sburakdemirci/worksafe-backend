package com.worksafe.backend.core.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetRequest {

    private String token;
    private String newPassword;
    private String newPasswordConfirm;

}
