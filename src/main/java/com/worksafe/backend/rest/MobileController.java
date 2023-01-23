package com.worksafe.backend.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.dto.response.AppVersionDto;

@RestController
@RequestMapping(value = "mobile")
public class MobileController {

    @GetMapping(value = "version")
    public AppVersionDto getAppVersion() {
        return AppVersionDto.builder()
                .currentVersion("1.0.0")
                .forceUpdate(false)
                .build();
    }
}
