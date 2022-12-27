package com.worksafe.backend.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.worksafe.backend.persistence.entity.Exercise;
import com.worksafe.backend.persistence.repository.ExerciseRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Transactional
    public Exercise save(Exercise exercise) {
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


}
