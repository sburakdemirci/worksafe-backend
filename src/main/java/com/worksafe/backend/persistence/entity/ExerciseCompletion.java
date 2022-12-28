package com.worksafe.backend.persistence.entity;

import static jakarta.persistence.TemporalType.TIMESTAMP;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ExerciseCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Exercise exercise;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(updatable = false)
    private Instant completedAt;


    @Builder

    public ExerciseCompletion(User user, Exercise exercise) {
        this.user = user;
        this.exercise = exercise;

    }
}
