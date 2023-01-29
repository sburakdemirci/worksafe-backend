package com.worksafe.backend.mobile.entity;

import com.worksafe.backend.mobile.enumarator.MobilePropertyType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class MobileProperty {

    @Id
    @Enumerated(EnumType.STRING)
    private MobilePropertyType property;

    private String value;

}
