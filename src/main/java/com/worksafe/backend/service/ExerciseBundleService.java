package com.worksafe.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.worksafe.backend.persistence.entity.ExerciseBundle;
import com.worksafe.backend.persistence.repository.ExerciseBundleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseBundleService {


    private ExerciseBundleRepository exerciseBundleRepository;

    @Transactional
    public ExerciseBundle save(ExerciseBundle exerciseBundle) {
        return exerciseBundleRepository.save(exerciseBundle);
    }

    @Transactional
    public void deleteById(Long id) {
        exerciseBundleRepository.deleteById(id);
    }

    public Optional<ExerciseBundle> findById(Long id) {
        return exerciseBundleRepository.findById(id);
    }

    public List<ExerciseBundle> getAll() {
        return exerciseBundleRepository.findAll();
    }


}

