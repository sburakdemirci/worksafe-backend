package com.worksafe.backend.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.dto.ExerciseDto;
import com.worksafe.backend.persistence.entity.Exercise;

@RestController
@RequestMapping("exercise")
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class ExerciseController {

    @PostMapping
    public Exercise create(@RequestBody ExerciseDto exerciseDto) {
        return null;
    }


}
