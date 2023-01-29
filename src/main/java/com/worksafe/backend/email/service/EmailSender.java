package com.worksafe.backend.email.service;

public interface EmailSender {

    void sendSingleTextEmail(String subject, String content, String recipientEmail);

}
