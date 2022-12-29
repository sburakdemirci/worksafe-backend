package com.worksafe.backend.rest;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worksafe.backend.dto.request.ExerciseDto;
import com.worksafe.backend.enumarator.BodyPart;
import com.worksafe.backend.persistence.entity.Exercise;
import com.worksafe.backend.security.CurrentUser;
import com.worksafe.backend.security.UserPrincipal;
import com.worksafe.backend.service.ExerciseService;
import com.worksafe.backend.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final UserService userService;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public Exercise create(@RequestBody ExerciseDto exerciseDto) {
        return exerciseService.save(exerciseDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{id}")
    public Exercise update(@PathVariable Long id, @RequestBody ExerciseDto exerciseDto) {
        return exerciseService.save(exerciseDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        exerciseService.deleteById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    public List<Exercise> getAllByBodyPart(@PathVariable BodyPart bodyPart) {

        return exerciseService.getAllByBodyPart(bodyPart);
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("complete-exercise/{id}")
    public void completeExercise(@PathVariable Long id, @CurrentUser UserPrincipal userPrincipal) {
        exerciseService.completeExerciseByUser(id, userPrincipal.getUser());
    }


}
