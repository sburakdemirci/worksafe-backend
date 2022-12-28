package com.worksafe.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {

    @Override
    public void sendEmail(String content, List<String> recipentEmails) {

    }
}
