package com.worksafe.backend.persistence.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class Exercise extends EntityAuditBase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private int duration;

    @NotNull
    private String subtitle;

    @NotNull
    private String description;
    //private Category category; ? Define category if needed or delete it

    @NotNull
    private String animationUrl;
}
