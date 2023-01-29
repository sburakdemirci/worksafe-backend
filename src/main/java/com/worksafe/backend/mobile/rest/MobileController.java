package com.worksafe.backend.mobile.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.mobile.response.AppVersionResponse;

@RestController
@RequestMapping(value = "mobile")
public class MobileController {

    @GetMapping(value = "version")
    public AppVersionResponse getAppVersion() {
        return AppVersionResponse.builder()
                .version("1.0.0")
                .forceUpdate(false)
                .build();
    }
}
