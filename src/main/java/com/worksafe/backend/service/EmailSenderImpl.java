package com.worksafe.backend.service;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailSenderImpl {

  /*  @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail() {
        String from = "info@movetodigital.net";
        String to = "gfb.salih95@gmail.com";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("This is a plain text email");
        message.setText("Hello guys! This is a plain text email.");

        javaMailSender.send(message);
    }*/
}
