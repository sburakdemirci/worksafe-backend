package com.worksafe.backend.exercise.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.worksafe.backend.exercise.request.ExerciseRequest;
import com.worksafe.backend.exercise.entity.Exercise;
import com.worksafe.backend.exercise.entity.ExerciseCompletion;
import com.worksafe.backend.core.user.entity.User;
import com.worksafe.backend.exercise.enumarator.BodyPart;
import com.worksafe.backend.exercise.repository.ExerciseCompletionRepository;
import com.worksafe.backend.exercise.repository.ExerciseRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseCompletionRepository exerciseCompletionRepository;

    @Transactional
    public Exercise save(ExerciseRequest exerciseDto) {
        return exerciseRepository.save(new Exercise(exerciseDto));
    }

    @Transactional
    public Exercise update(Long id, ExerciseRequest exerciseDto) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        exercise.update(exerciseDto);
        return exerciseRepository.save(exercise);
    }

    public Exercise findById(Long id) throws NotFoundException {
        return exerciseRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Transactional
    public void completeExerciseByUser(Long id, User user) {

        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());

        ExerciseCompletion exerciseCompletion = ExerciseCompletion.builder()
                .user(user)
                .exercise(exercise)
                .build();
        exerciseCompletionRepository.save(exerciseCompletion);

    }

    public List<Exercise> getAllByBodyPart(BodyPart bodyPart) {
        return exerciseRepository.getAllByBodyPart(bodyPart);
    }

}
