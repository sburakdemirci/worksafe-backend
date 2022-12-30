package com.worksafe.backend.service;

public interface EmailSender {

    void sendSingleTextEmail(String subject, String content, String recipientEmail);

}
