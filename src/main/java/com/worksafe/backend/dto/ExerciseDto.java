package com.worksafe.backend.dto;


import com.worksafe.backend.persistence.entity.EntityAuditBase;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExerciseDto extends EntityAuditBase {

    private String title;
    private int duration;
    private String subtitle;
    private String description;
    //private Category category; ? Define category if needed or delete it
    private String animationUrl;
}
