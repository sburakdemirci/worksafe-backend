package com.worksafe.backend.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.persistence.entity.ExerciseBundle;
import com.worksafe.backend.service.ExerciseBundleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("exercise-bundle")
@RequiredArgsConstructor
public class ExerciseBundleController {

    private final ExerciseBundleService exerciseBundleService;

    @GetMapping("/{id}")
    public ExerciseBundle getById(@RequestParam Long id) {
        return exerciseBundleService.findById(id).orElseThrow(() -> new RuntimeException());
    }


}
