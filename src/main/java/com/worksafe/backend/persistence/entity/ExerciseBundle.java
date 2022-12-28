package com.worksafe.backend.persistence.entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class ExerciseBundle extends EntityAuditBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;
    /*
        private ExerciseCategory exerciseCategory;
    */
    @Min(1)
    @Max(5)
    private int exerciseLevel;

    @NotNull
    private String imageUrl;

    @Min(1)
    private int totalDuration;

    //todo this will be a calculated field from exercise durations. It will be not a direct database field.

    //todo burak cascade
    @OneToMany(mappedBy = "exerciseBundle", cascade = CascadeType.ALL)
    private List<ExerciseBundleItem> exerciseBundleItems;

    private boolean visible;
}
