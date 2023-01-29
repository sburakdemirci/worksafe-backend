package com.worksafe.backend.exercise.entity;


import com.worksafe.backend.exercise.request.ExerciseRequest;
import com.worksafe.backend.exercise.enumarator.BodyPart;
import com.worksafe.backend.core.configuration.EntityAuditBase;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Exercise extends EntityAuditBase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String subtitle;

    @NotNull
    private String description;

    @NotNull
    private String animationUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BodyPart bodyPart;

    private boolean visible;


    public Exercise(ExerciseRequest exerciseDto) {
        this.title = exerciseDto.getTitle();
        this.subtitle = exerciseDto.getSubtitle();
        this.description = exerciseDto.getDescription();
        this.animationUrl = exerciseDto.getAnimationUrl();
        this.bodyPart = exerciseDto.getBodyPart();
        this.visible = true;
    }

    public Exercise update(ExerciseRequest exerciseDto) {
        this.title = exerciseDto.getTitle();
        this.subtitle = exerciseDto.getSubtitle();
        this.description = exerciseDto.getDescription();
        this.animationUrl = exerciseDto.getAnimationUrl();
        this.bodyPart = exerciseDto.getBodyPart();
        this.visible = true;
        return this;
    }


}
