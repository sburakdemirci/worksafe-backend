package com.worksafe.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeRequest {

    private String currentPassword;
    private String newPassword;
    private String newPasswordConfirm;

}
