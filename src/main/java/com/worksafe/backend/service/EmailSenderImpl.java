package com.worksafe.backend.service;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.worksafe.backend.dto.configuration.EmailSenderProperties;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {

    private final EmailSenderProperties emailSenderProperties;
    private final Session session;


    @Override
    public void sendSingleTextEmail(String subject, String content, String recipientEmail) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailSenderProperties.getEmail(),
                    emailSenderProperties.getAlias()));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
