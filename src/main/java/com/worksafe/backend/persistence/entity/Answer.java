package com.worksafe.backend.persistence.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@IdClass(AnswerOrderKey.class)
@Getter
public class Answer {

    @Id
    @ManyToOne
    private Quiz quiz;

    @Id
    @NotNull
    private Integer answerOrder;

    @NotNull
    private String text;

}

class AnswerOrderKey implements Serializable {
    private Long quiz;
    private int answerOrder;

    public AnswerOrderKey(Long quiz, int answerOrder) {
        this.quiz = quiz;
        this.answerOrder = answerOrder;
    }
}
