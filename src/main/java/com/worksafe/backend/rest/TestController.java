package com.worksafe.backend.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.security.CurrentUser;
import com.worksafe.backend.security.UserPrincipal;

@RestController
@RequestMapping("api")
public class TestController {

    @GetMapping("test")
    public String api(@CurrentUser UserPrincipal principal) {
        throw new RuntimeException();
        //  return "Successful";
    }

}
