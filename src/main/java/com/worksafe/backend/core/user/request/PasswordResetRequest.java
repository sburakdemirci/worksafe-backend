package com.worksafe.backend.core.user.request;

import lombok.Getter;
import lombok.Setter;

/**
 * This request will come from web or mobile. User will click the reset link in the email and when will type the new password. And send it along with the passwordResetToken
 */
@Getter
@Setter
public class PasswordResetRequest {

    private String passwordResetToken;
    private String newPassword;
    private String newPasswordConfirm;

}
