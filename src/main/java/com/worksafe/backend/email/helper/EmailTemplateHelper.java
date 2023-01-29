package com.worksafe.backend.email.helper;

import com.worksafe.backend.core.user.entity.User;

public class EmailTemplateHelper {

    public static String constructUserVerificationEmail(User user, String verificationUrl) {

        return String.format(
                "Hello %s , \n\n\nPlease click the link below to activate your account. \n %s",
                user.getName(), verificationUrl);

    }

}
