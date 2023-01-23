package com.worksafe.backend.dto.request;


import com.worksafe.backend.enumarator.BodyPart;

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
