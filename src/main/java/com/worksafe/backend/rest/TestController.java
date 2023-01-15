package com.worksafe.backend.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import com.worksafe.backend.exception.BurakException;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public void test(){
        System.out.println();
    }
}
