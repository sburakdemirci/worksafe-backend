package com.worksafe.backend.exercise.request;


import com.worksafe.backend.exercise.enumarator.BodyPart;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExerciseRequest {

    private String title;
    private String subtitle;
    private String description;
    private String animationUrl;
    private BodyPart bodyPart;


}
