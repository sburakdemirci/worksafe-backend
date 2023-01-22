package com.worksafe.backend.rest;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public void test(){
        System.out.println();
    }
}
