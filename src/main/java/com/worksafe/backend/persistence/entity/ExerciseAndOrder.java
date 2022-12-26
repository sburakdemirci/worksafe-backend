package com.worksafe.backend.persistence.entity;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
@IdClass(ExerciseOrderId.class)
public class ExerciseAndOrder {

    @Id
    @ManyToOne
/*
    @JoinColumn(name = "exercise_id", nullable = false)
*/
    private Exercise exercise;

    @Id
    @NotNull
    private Integer exerciseOrder;

    @ManyToOne
/*
    @JoinColumn(name = "exercise_bundle_id", nullable = false)
*/
    private ExerciseBundle exerciseBundle;


}

class ExerciseOrderId implements Serializable {

    public ExerciseOrderId(Long exercise, Integer exerciseOrder) {
        this.exercise = exercise;
        this.exerciseOrder = exerciseOrder;
    }

    private Long exercise;
    private Integer exerciseOrder;

}




