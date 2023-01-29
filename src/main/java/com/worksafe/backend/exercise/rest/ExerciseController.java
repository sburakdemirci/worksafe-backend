package com.worksafe.backend.exercise.rest;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.exercise.request.ExerciseRequest;
import com.worksafe.backend.exercise.enumarator.BodyPart;
import com.worksafe.backend.exercise.entity.Exercise;
import com.worksafe.backend.core.security.annotation.CurrentUser;
import com.worksafe.backend.core.security.configuration.UserPrincipal;
import com.worksafe.backend.exercise.service.ExerciseService;
import com.worksafe.backend.core.user.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("exercise")
@RequiredArgsConstructor

//swagger auth token configurer
@SecurityRequirement(name = "bearerAuth")

public class ExerciseController {

    private final ExerciseService exerciseService;
    private final UserService userService;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public Exercise create(@RequestBody ExerciseRequest exerciseDto) {
        return exerciseService.save(exerciseDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{id}")
    public Exercise update(@PathVariable Long id, @RequestBody ExerciseRequest exerciseDto) {
        return exerciseService.save(exerciseDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        exerciseService.deleteById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    public List<Exercise> getAllByBodyPart(@RequestParam BodyPart bodyPart) {

        return exerciseService.getAllByBodyPart(bodyPart);
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("complete-exercise/{id}")
    public void completeExercise(@PathVariable Long id, @CurrentUser UserPrincipal userPrincipal) {
        exerciseService.completeExerciseByUser(id, userPrincipal.getUser());
    }


}
