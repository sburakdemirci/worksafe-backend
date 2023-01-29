package com.worksafe.backend.mobile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.worksafe.backend.mobile.entity.MobileProperty;
import com.worksafe.backend.mobile.enumarator.MobilePropertyType;

@Repository
public interface MobilePropertiesRepository extends JpaRepository<MobileProperty, MobilePropertyType> {

    Optional<MobileProperty> findByProperty(MobilePropertyType mobilePropertyType);
}
