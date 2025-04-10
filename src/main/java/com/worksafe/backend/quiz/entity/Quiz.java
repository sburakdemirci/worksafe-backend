package com.worksafe.backend.quiz.entity;

import java.util.List;

import com.worksafe.backend.core.configuration.EntityAuditBase;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Quiz extends EntityAuditBase {

    @OneToMany(mappedBy = "quiz")
    List<Answer> answers;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;

}
