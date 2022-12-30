package com.worksafe.backend.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.service.EmailSender;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailSender emailSender;


    @GetMapping
    public void sendEmail() {
       /* emailSender.sendSingleTextEmail("Mail Basligi",
                "Bu bir test emailidir. \n yeni satir morukss", "burak@movetodigital.net");*/
    }

}
