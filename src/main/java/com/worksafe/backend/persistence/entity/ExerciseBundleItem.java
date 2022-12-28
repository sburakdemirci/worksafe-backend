package com.worksafe.backend.persistence.entity;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
@IdClass(ExerciseBundleItemPK.class)
public class ExerciseBundleItem {

    @Id
    @ManyToOne
    private Exercise exercise;

    @Id
    @NotNull
    private Integer exerciseOrder;

    @ManyToOne
    private ExerciseBundle exerciseBundle;

    private int duration;
    private int repeatCount;

}

class ExerciseBundleItemPK implements Serializable {

    private final Long exercise;
    private final Integer exerciseOrder;

    public ExerciseBundleItemPK(Long exercise, Integer exerciseOrder) {
        this.exercise = exercise;
        this.exerciseOrder = exerciseOrder;
    }

}




