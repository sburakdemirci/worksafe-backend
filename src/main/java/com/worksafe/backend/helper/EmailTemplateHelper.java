package com.worksafe.backend.helper;

import com.worksafe.backend.persistence.entity.User;

public class EmailTemplateHelper {

    public static String constructUserVerificationEmail(User user, String verificationUrl) {

        return String.format(
                "Hello %s %s, \n\n\nPlease click the link below to activate your account. \n %s",
                user.getFirstName(), user.getLastName(), verificationUrl);

    }

}
