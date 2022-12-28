package com.worksafe.backend.service;

import java.util.List;

public interface EmailSender {

    void sendEmail(String content, List<String> recipentEmails);

}
